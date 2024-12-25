package csu.web.mypetstore.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="accountUpdate",urlPatterns = {"/web/accountUpdate"})
public class AccountUpdateServlet extends HttpServlet {

    private static final String Update_Form = "/WEB-INF/jsp/account/update.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        String Current_username="";
//        String Current_password="";
//        if (session != null) {
//            Current_username = (String) session.getAttribute("Current_username");
//            Current_password = (String) session.getAttribute("Current_Password");
//        }
//        System.out.println(Current_username);
//
//        req.setAttribute("Current_username",Current_username);
//        req.setAttribute("Current_Password",Current_password);
        req.getRequestDispatcher(Update_Form).forward(req, resp);

    }
}
