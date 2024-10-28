package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoImpl {

    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD="SELECT"+
            "SIGNON.USERNAME,"+
    "ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME, ACCOUNT.LASTNAME, ACCOUNT.STATUS,"+
    "ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2,"+
    "ACCOUNT.CITY, ACCOUNT.STATE, ACCOUNT.ZIP, ACCOUNT.COUNTRY, ACCOUNT.PHONE,"+
    "PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryId,"+
    "PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption,"+
    "BANNERDATA.BANNERNAME"+
    "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA"+
    "WHERE ACCOUNT.USERID = #{username}"+
    "AND SIGNON.USERNAME = ACCOUNT.USERID"+
    "AND PROFILE.USERID = ACCOUNT.USERID"+
    "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    Account getAccountByUsername(String username){
        return null;
    };

    Account getAccountByUsernameAndPassword(Account account)throws Exception{        Account accountResult=null;
        try{
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                accountResult=this.resultSetToAccount(resultSet);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountResult;
    };

    private Account resultSetToAccount(ResultSet resultSet)throws  Exception{
        return null;
    };
    void insertAccount(Account account){};

    void insertProfile(Account account){};

    void insertSignon(Account account){};

    void updateAccount(Account account){};

    void updateProfile(Account account){};

    void updateSignon(Account account){};
}
