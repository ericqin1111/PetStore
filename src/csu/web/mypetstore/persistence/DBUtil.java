package csu.web.mypetstore.persistence;

import java.sql.*;

public class DBUtil {
    private static final String Driver = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/myfirstpetstore";
    private static final String USERNAME = "root";

    private static final String PASSWORD = "238947";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(Driver);
            System.out.println("dbutil:Connecting to database...");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("dbutil:Connected!");
        } catch (ClassNotFoundException e) {
            System.out.println("dbutil:Can't find JDBC driver!");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("dbutil:Can't connect to database!");
            throw new RuntimeException(e);
        }
        return connection;
    }


    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void closeStatement(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    public static void closeResultSet(ResultSet resultSet) throws SQLException {
        if(resultSet!=null)
        {
            resultSet.close();
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}


