package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.persistence.impl.OperateLoadCartImpl;
import csu.web.mypetstore.service.CatelogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class AddItemToCartServlet extends HttpServlet {

    //private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String workingItemId = req.getParameter("workingItemId");
        //System.out.println("adding cartss " + workingItemId);
        HttpSession session = req.getSession();

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }

        if (cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);   //ddddddd
            try {
                OperateLoadCartImpl.IncreaseQuantity(req, resp, workingItemId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            CatelogService catelogService = new CatelogService();
            boolean isInStock = catelogService.isItemInStock(workingItemId);
            Item item = catelogService.getItem(workingItemId);
            try {
                OperateLoadCartImpl.InsertCart(req, resp, workingItemId );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            cart.addItem(item, isInStock);
        }

        session.setAttribute("cart", cart);
        System.out.println(getServletContext());
        resp.sendRedirect("cartForm");
    }
}
