package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/userInfChange")
public class UserInfChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userInput = req.getParameter("input");
        String id = req.getParameter("userId");
        System.out.println(id);
        System.out.println(userInput);
        System.out.println("ucs:1");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        System.out.println("ucs:2");
        if(id.equals( "email")) {
            System.out.println("ucs:2.5");
            account.setEmail(userInput);
            System.out.println("ucs:3");
        }
        else if(id.equals( "phone")){
            account.setPhone(userInput);
        }
        else if(id.equals( "firstName")){
            account.setFirstName(userInput);
        }
        else if(id.equals( "lastName")){
            account.setLastName(userInput);
        }
        else if(id.equals( "address1")){
            account.setAddress1(userInput);
        }
        else if(id.equals( "address2")){
            account.setAddress2(userInput);
        }
        else if(id.equals( "city")){
            account.setCity(userInput);
        }
        else if(id.equals( "country")){
            account.setCountry(userInput);
        }

    }
}
