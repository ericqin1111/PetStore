package csu.web.mypetstore.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOnFormServlet extends HttpServlet {

    private static final String SIGN_ON_Form="/WEB-INF/jsp/account/signon.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("signonformm:1");
        req.getRequestDispatcher(SIGN_ON_Form).forward(req,resp);
    }
}
