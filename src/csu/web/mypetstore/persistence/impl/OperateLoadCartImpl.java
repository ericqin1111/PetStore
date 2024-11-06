package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.service.CatelogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class OperateLoadCartImpl {
    private static String CREATE_SAVE_TABLE = "CREATE TABLE CART ("
            + "USERNAME VARCHAR(25) NOT NULL,"
            + "ITEMID VARCHAR(10) NOT NULL,"
            + "QUANTITY INTEGER NOT NULL)";

    private static String LOAD_TABLE = "SELECT * FROM CART WHERE USERNAME = ?";
    private static String INSERT_TABLE = "INSERT INTO CART VALUES(?,?,?)";
    private static String DELETE_TABLE = "DELETE FROM CART WHERE USERNAME = ? AND ITEMID = ?";
    private static String INCREASE_QUANTITY = "UPDATE CART SET QUANTITY = QUANTITY + 1 WHERE USERNAME = ? AND ITEMID = ?";
    private static String SET_QUANTITY = "UPDATE CART SET QUANTITY = ? WHERE USERNAME = ? AND ITEMID = ?";

    private static void createTable() throws SQLException {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(CREATE_SAVE_TABLE);
        ps.executeUpdate();
        System.out.println("success in create table cart");
        DBUtil.closePreparedStatement(ps);
        DBUtil.closeConnection(conn);
    }


    //载入数据的接口
    public static void loadCart(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        Connection conn = DBUtil.getConnection();
        DatabaseMetaData dbmd = conn.getMetaData();;
        ResultSet rs = dbmd.getTables(null, null, "CART", null);
        if (!rs.next()) {
            createTable();
        }
        //HttpSession session = req.getSession();
        //System.out.println("SaveLoadCartimpl: 1");
        //Account loginAccount = (Account) session.getAttribute("loginAccount");
        //System.out.println("SaveLoadCartimpl: 2");
        loadData(req);
    }


    //加载数据库中相应的购物车数据，在登陆后才可使用
    private static void loadData(HttpServletRequest req) throws SQLException {


        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(LOAD_TABLE);
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        ps.setString(1, account.getUsername());
        ResultSet rs = ps.executeQuery();
        Cart cart = new Cart();
        CatelogService catelogService = new CatelogService();

        String itemId;
        int quantity;
        while (rs.next()) {
            itemId = rs.getString("ITEMID");
            quantity = rs.getInt("QUANTITY");
            boolean isInStock = catelogService.isItemInStock(itemId);
            Item item = catelogService.getItem(itemId);
            cart.addItem(item, isInStock);
            cart.setQuantityByItemId(itemId, quantity);
        }
        session.setAttribute("cart", cart);
        DBUtil.closeResultSet(rs);
        DBUtil.closePreparedStatement(ps);
        DBUtil.closeConnection(conn);
    }



    //插入数据库操作
    public static void InsertCart(HttpServletRequest req, HttpServletResponse resp, String itemId) throws SQLException {
        Connection conn = DBUtil.getConnection();
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");

        PreparedStatement ps = conn.prepareStatement(INSERT_TABLE);
        ps.setString(1, account.getUsername());
        ps.setString(2, itemId);
        ps.setInt(3, 1);
        ps.executeUpdate();
        DBUtil.closePreparedStatement(ps);
        DBUtil.closeConnection(conn);
    }

    public static void DeleteCart(HttpServletRequest req, HttpServletResponse resp, String itemId) throws SQLException {
        Connection conn = DBUtil.getConnection();
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");

        PreparedStatement ps = conn.prepareStatement(DELETE_TABLE);
        ps.setString(1, account.getUsername());
        ps.setString(2, itemId);
        ps.executeUpdate();
        DBUtil.closePreparedStatement(ps);
        DBUtil.closeConnection(conn);
    }
    public static void IncreaseQuantity(HttpServletRequest req, HttpServletResponse resp, String itemId) throws SQLException {
        Connection conn = DBUtil.getConnection();
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");

        PreparedStatement ps = conn.prepareStatement(INCREASE_QUANTITY);
        ps.setString(1, account.getUsername());
        ps.setString(2, itemId);
        ps.executeUpdate();
    }
    public static void SetQuantity(HttpServletRequest req, HttpServletResponse resp, String itemId, int quantity) throws SQLException {
        Connection conn = DBUtil.getConnection();
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");

        PreparedStatement ps = conn.prepareStatement(SET_QUANTITY);
        ps.setInt(1, quantity);
        ps.setString(2, account.getUsername());
        ps.setString(3, itemId);
        ps.executeUpdate();
    }

}
