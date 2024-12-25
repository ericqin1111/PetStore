package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDaoImpl implements AccountDao {

    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD = "SELECT " +
            "SIGNON.USERNAME," +
            "SIGNON.PASSWORD," +
            "ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS," +
            "ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2," +
            "ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE," +
            "PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryId," +
            "PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption," +
            "BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA " +
            "WHERE ACCOUNT.USERID = ? AND SIGNON.PASSWORD = ? " +
            "AND SIGNON.USERNAME = ACCOUNT.USERID " +
            "AND PROFILE.USERID = ACCOUNT.USERID " +
            "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static final String INSERT_ACCOUNT = "INSERT INTO SIGNON (" +
            "USERNAME, " +
            "PASSWORD" +
            ") VALUES (?, ?)";

    private static final String UPDATE_ACCOUNT = "UPDATE " +
            "SIGNON " +
            "SET " +
            "PASSWORD = ? " +
            "WHERE USERNAME = ?";



    @Override
    public Account getAccountByUsername(String username) {
        Account accountResult = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                accountResult = this.resultSetToAccount(resultSet);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accountResult;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account accountResult = null;
        try {

            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                System.out.println("account dao impl : 1");
                accountResult = this.resultSetToAccount(resultSet);
            }System.out.println("account dao impl : 2");
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            System.out.println("account dao impl : failure1");
            e.printStackTrace();
        }
        return accountResult;
    }

    private Account resultSetToAccount(ResultSet resultSet) throws Exception{
        Account account = new Account();

        if (resultSet == null){
            System.out.println("Accountdaoimpl:bad");
        }
        account.setUsername(resultSet.getString("username"));
        System.out.println("Accountdaoimpl:1");
        account.setPassword(resultSet.getString("password"));
        System.out.println("Accountdaoimpl:2");
        account.setEmail(resultSet.getString("email"));
        account.setFirstName(resultSet.getString("firstName"));
        account.setLastName(resultSet.getString("lastName"));
        account.setStatus(resultSet.getString("status"));

        account.setAddress1(resultSet.getString("address1"));
        account.setAddress2(resultSet.getString("address2"));
        account.setCity(resultSet.getString("city"));
        account.setState(resultSet.getString("state"));
        account.setZip(resultSet.getString("zip"));
        account.setCountry(resultSet.getString("country"));
        account.setPhone(resultSet.getString("phone"));
        account.setFavouriteCategoryId(resultSet.getString("favouriteCategoryId"));
        account.setLanguagePreference(resultSet.getString("languagePreference"));
        account.setListOption(resultSet.getInt("listOption") == 1);
        account.setBannerOption(resultSet.getInt("bannerOption") == 1);
        account.setBannerName(resultSet.getString("bannerName"));

        return account;
    }

    @Override
    public void insertAccount(Account account) {
        try {
            // 获取数据库连接
            Connection connection = DBUtil.getConnection();

            String sql = INSERT_ACCOUNT;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 设置SQL语句的参数
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
//            preparedStatement.setString(3, account.getEmail());
            // 执行插入操作
            int rowsAffected = preparedStatement.executeUpdate();
            // 关闭资源
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        } catch (SQLException e) {
            // 记录异常到日志中
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error inserting account", e);
            throw new RuntimeException("Error inserting account", e);
        }
    }

    @Override
    public void updateAccount(String username,String newPassword) {

        try {
            // 获取数据库连接
            Connection connection = DBUtil.getConnection();
            String sql = UPDATE_ACCOUNT;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 设置SQL语句的参数
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            // 更新
            int affectedRows = preparedStatement.executeUpdate(); // 执行更新操作
            if (affectedRows > 0) {
                System.out.println("Password updated successfully!");
            } else {
                System.out.println("No rows were updated.");
            }
            // 关闭资源
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        } catch (SQLException e) {
            // 记录异常到日志中
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error update account", e);
            throw new RuntimeException("Error updating account", e);
        }



        }

    @Override
    public void insertProfile(Account account) {

    }

    @Override
    public void insertSignon(Account account) {

    }



    @Override
    public void updateProfile(Account account) {

    }

    @Override
    public void updateSignon(Account account) {

    }


//    public static void main(String[] args) {
////        AccountDao accountDao = new AccountDaoImpl();
////        Account account = new Account();
////        account.setUsername("j2ee");
////        account.setPassword("jee");
////        Account result = accountDao.getAccountByUsernameAndPassword(account);
////        System.out.println("success");
//    }
}
