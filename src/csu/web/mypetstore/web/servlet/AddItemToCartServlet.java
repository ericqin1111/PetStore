package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.service.CatelogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {

    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String workingItemId = req.getParameter("workingItemId");
        System.out.println("adding cartss " + workingItemId);
        HttpSession session = req.getSession();

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }

        if (cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);
        } else {
            CatelogService catelogService = new CatelogService();
            boolean isInStock = catelogService.isItemInStock(workingItemId);
            Item item = catelogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
        }

        session.setAttribute("cart", cart);
        req.getRequestDispatcher(CART_FORM).forward(req, resp);
    }
}
