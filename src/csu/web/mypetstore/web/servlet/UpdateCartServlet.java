package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.persistence.impl.OperateLoadCartImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class UpdateCartServlet extends HttpServlet {

    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("UpdateCartServlet: 1");
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            Iterator<CartItem> cartItems = cart.getAllCartItems();

        while (cartItems.hasNext()) {
            CartItem cartItem = (CartItem) cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                String quantityString = req.getParameter(itemId);
                int quantity = Integer.parseInt(quantityString);

                cart.setQuantityByItemId(itemId, quantity);
                OperateLoadCartImpl.SetQuantity(req, resp, itemId, quantity);
                if (quantity < 1) {
                    cartItems.remove();
                    OperateLoadCartImpl.DeleteCart(req, resp, itemId);
                }
            } catch (Exception e) {
                System.out.println("UpdateCartServlet: " + e.getMessage());
                //ignore parse exceptions on purpose
            }
        }
        }
        req.getRequestDispatcher(CART_FORM).forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
