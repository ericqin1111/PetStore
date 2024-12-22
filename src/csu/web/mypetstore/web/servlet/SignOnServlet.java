package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.AccountService;
import csu.web.mypetstore.service.CatelogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SignOnServlet extends HttpServlet {

    private static final String SIGN_ON_Form="/WEB-INF/jsp/account/signon.jsp";
    private String username;
    private String password;
    private String vcode;
    private String msg;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.username=req.getParameter("username");
//        System.out.println("SignonSevlet:1");
        this.password=req.getParameter("password");
        this.vcode=req.getParameter("vcode");
        HttpSession session=req.getSession();
        String code=(String) session.getAttribute("code");
//        System.out.println("SignonSevlet:2");
        //检验用户输入的正确性
        if(!validate()){
            req.setAttribute("signOnMsg",this.msg);
            req.getRequestDispatcher(SIGN_ON_Form).forward(req,resp);
        } else if (!vcode.equalsIgnoreCase(code)) {
            this.msg="验证码错误";
            req.setAttribute("signOnMsg",this.msg);
            req.getRequestDispatcher(SIGN_ON_Form).forward(req,resp);

        } else {
            AccountService accountService=new AccountService();
            Account loginAccount=accountService.getAccount(username,password);
            if(loginAccount==null) {
                System.out.println("bad enter from signonservlet");
                this.msg = "用户名或密码错误";
                req.getRequestDispatcher(SIGN_ON_Form).forward(req, resp);
            }  else{
//                    HttpSession session=req.getSession();
                    session.setAttribute("loginAccount",loginAccount);

                    if(loginAccount.isListOption()){
                        CatelogService catelogService=new CatelogService();
                        List<Product> myList=catelogService.getProductListByCategory(loginAccount.getFavouriteCategoryId());
                        session.setAttribute("myList",myList);
                    }
                    resp.sendRedirect("mainFrom");
                }
        }
    }
    private boolean validate(){
        if(this.username==null||this.username.equals("")){
            this.msg="用户名不能为空";
            return  false;
        }
        if(this.password==null||this.password.equals("")){
            this.msg="密码不能为空";
            return  false;
        }
        System.out.println("valid");
        return true;
    }
}

