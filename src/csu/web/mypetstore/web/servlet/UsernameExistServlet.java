package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.impl.AccountDaoImpl;
@WebServlet(name="usernameIsExist",urlPatterns = {"/usernameIsExist"})
public class UsernameExistServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        //调用service，dao查询username是否可用
        //        AccountService accountService=new AccountService();
//        Account account=new Account();
//        account.setUsername(username);
//        accountService.NameIsExist(account)

        resp.setContentType("text/plain");//响应结果是一个字符串
        PrintWriter out=resp.getWriter();

        if(username.equals("12345")){
            out.print("Exist");
        }else{
            out.print("Not Exist");
        }
    }
}
