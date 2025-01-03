
package csu.web.mypetstore.web.servlet;


import csu.web.mypetstore.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewOrderFormServlet extends HttpServlet {
    private static final String New_ORDER_Form="/WEB-INF/jsp/order/newOrder.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Account loginAccount=(Account) session.getAttribute("loginAccount");
        if(loginAccount==null){//用户没有登录
            resp.sendRedirect("signonForm");
        }
        else {//用户已登录
            req.getRequestDispatcher(New_ORDER_Form).forward(req,resp);
        }
    }
}
