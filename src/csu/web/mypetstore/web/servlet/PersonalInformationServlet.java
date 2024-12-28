package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/personalInformation")
public class PersonalInformationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println("pis:1");
        String shown = req.getParameter("shown");
        Account account = (Account) session.getAttribute("loginAccount");
        System.out.println(account.getEmail());
        String content1 = "<tr>" +
                "<td >Email:" + "</td> <td class='personinf' id='email'>" + account.getEmail() + "   </td>" +
                "</tr>"+
                "<tr> " +
                "<td > Name:" + "</td> <td class='personinf' id='firstName'>" + account.getFirstName() +"</td> " +
                "<td class='personinf' id='lastName'>"+ account.getLastName() +"   </td>" +
                "</tr>"+
                "<tr> " +
                "<td > Address:" + "</td>" +
                " <td class='personinf' id='address1'>" + account.getAddress1() + " </td>" +
                "<td  class='personinf' id='address2'>"+ account.getAddress2()+ "</td>" +
                "<td  class='personinf' id='city'>" + account.getCity() +  "</td>" +
                "<td  class='personinf' id='country'>" + account.getCountry() +"   </td>" +
                "</tr>" +
                "<tr>" +
                "<td> Number: "  + "</td> <td class='personinf' id='phone'>" + account.getPhone() + "</td>" +
                "</tr>";
        String content2 = "<tr>" +
                "<td >" + "</td> <td class='personinf' id='email'> </td>" +
                "</tr>"+
                "<tr> " +
                "<td >" + "</td> <td class='personinf' id='firstName'></td> " +
                "<td class='personinf' id='lastName'>  </td>" +
                "</tr>"+
                "<tr> " +
                "<td >" + "</td>" +
                " <td class='personinf' id='address1'> </td>" +
                "<td  class='personinf' id='address2'></td>" +
                "<td  class='personinf' id='city'></td>" +
                "<td  class='personinf' id='country'>  </td>" +
                "</tr>" +
                "<tr>" +
                "<td>  "  + "</td> <td class='personinf' id='phone'></td>" +
                "</tr>";;
        if (Objects.equals(shown, "false")) {
            resp.getWriter().write(content1);
        }else{
            resp.getWriter().write(content2);
        }
    }
}
