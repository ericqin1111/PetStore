package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Category;
import csu.web.mypetstore.persistence.CategoryDao;
import csu.web.mypetstore.persistence.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private static final String GET_CATEGORY_LIST = "SELECT CATID AS categoryId,NAME,DESCN AS description FROM CATEGORY";
    private static final String GET_CATEGORY = "SELECT CATID AS categoryId,NAME,DESCN AS description FROM CATEGORY WHERE CATID = ?";

    public List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_CATEGORY_LIST);
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getString("categoryId"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                categoryList.add(category);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }


    @Override
    public Category getCategory(String categoryId) {
        Category category = null;
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            System.out.println("categorydaoimpl1");

            PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY);
            System.out.println("categorydaoimpl2");
            preparedStatement.setString(1,categoryId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                category = new Category();
                System.out.println(resultSet.getString("categoryId"));
                category.setCategoryId(resultSet.getString("categoryId"));
                category.setName(resultSet.getString("NAME"));
                category.setDescription(resultSet.getString("description"));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("categorydaoimpl3");
        return category;
    }

}

