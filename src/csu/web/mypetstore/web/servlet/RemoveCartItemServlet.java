package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.persistence.impl.OperateLoadCartImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

public class RemoveCartItemServlet extends HttpServlet {

    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        String workingItemId = req.getParameter("workingItemId");


        Item item = cart.removeItemById(workingItemId);
        try {
            OperateLoadCartImpl.DeleteCart(req, resp, workingItemId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (item == null) {
            session.setAttribute("errorMsg", "Attempted to remove null CartItem from Cart.");
            req.getRequestDispatcher(ERROR_FORM).forward(req, resp);
        } else {
            resp.sendRedirect("cartForm");
        }
    }
}
