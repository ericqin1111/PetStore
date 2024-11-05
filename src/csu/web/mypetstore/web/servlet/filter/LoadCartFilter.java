package csu.web.mypetstore.web.servlet.filter;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.persistence.impl.OperateLoadCartImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(urlPatterns = {"/addItemToCart", "/updateCart", "/removeCartItem", "/cartForm"})
public class LoadCartFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        System.out.println(account);
        if (account == null) {

            HttpServletResponse response = (HttpServletResponse) servletResponse;
            System.out.println("LoadCartFilter: no login account");
            response.sendRedirect("signonForm");
        }
        else {
            Boolean flag = (Boolean) session.getAttribute("dataLoaded");
            if (flag == null) {
                try {
                    OperateLoadCartImpl.loadCart((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
                    session.setAttribute("dataLoaded", true);
                } catch (SQLException e) {
                    System.out.println("Load Cart Filter Error:1");
                    throw new RuntimeException(e);
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
