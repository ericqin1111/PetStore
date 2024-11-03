package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.impl.AccountDaoImpl;
import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求和响应
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        Map<String,String[]> parameterMap=req.getParameterMap();
//        Set<Map.Entry<String,String[]>> entries=parameterMap.entrySet();
//        for (Map.Entry<String,String[]> map:entries){
//            System.out.println(map.getKey()+"\t"+map.getValue());
//        }测试输出

        //获取请求参数
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String email=req.getParameter("email");

        //把请求参数封装成实体类
        Account account=new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);

        //调用service，进行注册业务处理
        AccountService accountService=new AccountService();
        try {
            accountService.doRegister(account);
            //注册成功
            resp.sendRedirect("signonForm");
        }
        catch (Exception e){//注册失败返回注册页面
            resp.sendRedirect("RegisterForm");
            throw new RuntimeException(e);


        }


        //响应客户端浏览器


        //
    }
}
