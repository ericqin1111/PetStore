package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.persistence.impl.OperateLoadCartImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;



public class NumberChangeCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String itemID = req.getParameter("itemID");
        int newVal = Integer.parseInt(req.getParameter("newVal"));

        System.out.println("numberChanged");
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (newVal > 0) {
            cart.setQuantityByItemId(itemID, newVal);
            try {
                OperateLoadCartImpl.SetQuantity(req, resp, itemID, newVal);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
