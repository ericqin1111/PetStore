package csu.web.mypetstore.web.servlet.filter;

import csu.web.mypetstore.persistence.DBUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class logFilter implements Filter {

    DBUtil dbUtil=new DBUtil();
    Connection connection=dbUtil.getConnection();

    private final Map<String,KeywordHandler> keywordHandlers=new HashMap<>();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        keywordHandlers.put("categoryId",this::insertIntoViewlog);
        keywordHandlers.put("productId",this::insertIntoViewlog);
        keywordHandlers.put("itemId",this::insertIntoViewlog);

        keywordHandlers.put("workingItemId",this::insertIntoCartlog);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;

        httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);

        Map<String,String>parameters=new HashMap<>();
        String keyword=httpServletRequest.getParameter("act");
        if(keyword!=null)
        {
            for(String handlersKeyword: keywordHandlers.keySet())
            {
                String value=httpServletRequest.getParameter(handlersKeyword);
                if(value!=null)
                {
                    parameters.put(handlersKeyword,value);
                }
            }

            for(Map.Entry<String,String>entry:parameters.entrySet())
            {
                String handlersKeyword=entry.getKey();
                String value=entry.getValue();
                try {
                    keywordHandlers.get(handlersKeyword).handle(value);
                    System.out.println("插入到表: " + handlersKeyword + ", 值: " + value);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
        try {
            dbUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Filter.super.destroy();
    }

    public void insertIntoViewlog(String value) throws SQLException {
        String SQL="INSERT INTO viewlog(viewhistory) VALUES (?)";
        PreparedStatement preparedStatement=connection.prepareStatement(SQL);
        preparedStatement.setString(1,value);
        preparedStatement.executeUpdate();
    }
    private void insertIntoCartlog(String value) throws SQLException {
        String SQL="INSERT INTO cartlog(carthistory) VALUES (?)";
        PreparedStatement preparedStatement=connection.prepareStatement(SQL);
        preparedStatement.setString(1,value);
        preparedStatement.executeUpdate();
    }
    private void insertIntoSubmitlog(String value) throws SQLException {
        String SQL="INSERT INTO submitlog(submithistory) VALUES (?)";
        PreparedStatement preparedStatement=connection.prepareStatement(SQL);
        preparedStatement.setString(1,value);
        preparedStatement.executeUpdate();
    }
    interface KeywordHandler{
        void handle(String value) throws SQLException;
    }
}
