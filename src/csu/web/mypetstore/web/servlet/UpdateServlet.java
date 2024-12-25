package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.impl.AccountDaoImpl;
import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UpdateServlet extends HttpServlet {

    public String UpdateMsg;

    private String vcode;


    private static final String Update_Form="/WEB-INF/jsp/account/update.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String Current_username=(String) req.getParameter("Current_username");
//        String Current_password=(String) req.getParameter("Current_password");
//        System.out.println(Current_username);
        doPost(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置请求和响应
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        Map<String,String[]> parameterMap=req.getParameterMap();

        //获取请求参数
        String Current_username=req.getParameter("Current_username");
        String Current_password=req.getParameter("Current_password");
        String password=req.getParameter("password");
        String password1=req.getParameter("password1");
        String password2=req.getParameter("password2");
        System.out.println(Current_username);

//        //把请求参数封装成实体类
//        Account account=new Account();
//        account.setUsername(Current_username);
//        account.setPassword(Current_password);
//        account.setPassword(password);


        this.vcode=req.getParameter("vcode");
        HttpSession session=req.getSession();
        String code=(String) session.getAttribute("code");
        if(!vcode.equalsIgnoreCase(code)){
            this.UpdateMsg="修改失败，验证码错误";
            req.setAttribute("UpdateMsg", this.UpdateMsg);
            req.getRequestDispatcher(Update_Form).forward(req, resp);
        }
//        else if(!Current_password.equals(password1)){
//            this.UpdateMsg="修改失败，原密码错误";
//            req.setAttribute("UpdateMsg", this.UpdateMsg);
//            req.getRequestDispatcher(Update_Form).forward(req, resp);
//            HttpSession session2 = req.getSession();
//            session.setAttribute("Current_username", Current_username);
//            session.setAttribute("Current_password", Current_password);
//
//        }
//            sessionScope.loginAccount==null;
        //调用service，进行注册业务处理
        AccountService accountService=new AccountService();
        try {
//            accountService.updateAccount(Current_username,password1);
            accountService.updateAccount("j2ee",password1);
            //注册成功
            resp.sendRedirect("signonForm");
        }
        catch (Exception e){//注册失败返回注册页面
            this.UpdateMsg = "修改失败";
            req.setAttribute("UpdateMsg", this.UpdateMsg);
            req.getRequestDispatcher(Update_Form).forward(req, resp);
//            resp.sendRedirect("RegisterForm");
        }
    }
}
