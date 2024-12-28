package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Log;
import csu.web.mypetstore.persistence.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class LogFormServlet extends HttpServlet {
    private static final String LOG_FORM = "/WEB-INF/jsp/catalog/log.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        String pageStr=req.getParameter("page");
        int page = (pageStr != null) ? Integer.parseInt(pageStr) : 1;
        int pageSize=10;
        int offset=(page-1)*pageSize;
        int totalRecord=0;
        int totalPages=0;
        ArrayList<Log> logList = new ArrayList<>();

        HttpSession session=req.getSession();
        String tableName = req.getParameter("type");
        session.setAttribute("type",tableName);
        session.setAttribute("page",page);

        String SQL = "SELECT time,history FROM " + tableName +" ORDER BY time DESC LIMIT ? OFFSET ?";
        String SQLCount="SELECT COUNT(*) FROM "+ tableName;

        Log log = null;
        DBUtil dbUtil = new DBUtil();
        Connection connection = dbUtil.getConnection();

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setInt(1,pageSize);
            preparedStatement.setInt(2,offset);
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()) {
                log = new Log();
                log.setTime(rs.getTimestamp("time"));
                log.setDocument(rs.getString("history"));
                logList.add(log);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {

            Statement statement2=connection.createStatement();
            ResultSet rs2=statement2.executeQuery(SQLCount);
            if(rs2.next())
            {
                totalRecord=rs2.getInt(1);
            }
            totalPages=(int)Math.ceil(totalRecord/(double)pageSize);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("logList", logList);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPage",totalPages);

        try {
            dbUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher(LOG_FORM).forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
