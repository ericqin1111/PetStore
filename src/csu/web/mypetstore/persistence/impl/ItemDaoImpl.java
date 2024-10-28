package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Category;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.ItemDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDaoImpl implements ItemDao {

    public static final String GET_ITEMLIST_BY_PRODUCT=
            "SELECT " +
            "I.ITEMID," +
            "LISTPRICE," +
            "UNITCOST," +
            "SUPPLIER AS supplierId," +
            "I.PRODUCTID AS \"product.productId\"," +
            "NAME AS \"product.name\"," +
            "DESCN AS \"product.description\"," +
            "CATEGORY AS \"product.categoryId\"," +
            "STATUS," +
            "ATTR1 AS attribute1," +
            "ATTR2 AS attribute2," +
            "ATTR3 AS attribute3," +
            "ATTR4 AS attribute4," +
            "ATTR5 AS attribute5 " +
            "FROM ITEM I, PRODUCT P " +
            "WHERE P.PRODUCTID = I.PRODUCTID " +
            "AND I.PRODUCTID = ?";

    public static final String GET_ITEM=
            "SELECT " +
                    "I.ITEMID," +
                    "LISTPRICE," +
                    "UNITCOST," +
                    "SUPPLIER AS supplierId," +
                    "I.PRODUCTID AS \"product.productId\"," +
                    "NAME AS \"product.name\"," +
                    "DESCN AS \"product.description\"," +
                    "CATEGORY AS \"product.categoryId\"," +
                    "STATUS," +
                    "ATTR1 AS attribute1," +
                    "ATTR2 AS attribute2," +
                    "ATTR3 AS attribute3," +
                    "ATTR4 AS attribute4," +
                    "ATTR5 AS attribute5, " +
                    "QTY AS quantity " +
                    "from ITEM I, INVENTORY V, PRODUCT P " +
                    "WHERE P.PRODUCTID = I.PRODUCTID " +
                    "and I.ITEMID = V.ITEMID " +
                    "AND I.ITEMID = ? ";

    public static final String GET_INVENTORYQUANTITY=
            "SELECT QTY AS value FROM INVENTORY WHERE ITEMID = ?";

    public static final String UPDATE_INVENTROYQUANTITY=
            "UPDATE INVENTORY SET QTY = QTY - ? WHERE ITEMID = ?";

    public void updateInventoryQuantity(Map<String, Object> param) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_INVENTROYQUANTITY);
            int increament=(Integer) param.get("increament");
            String itemId=(String)param.get("itemId");

            preparedStatement.setInt(1,increament);
            preparedStatement.setString(2,itemId);

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getInventoryQuantity(String itemId)
    {
        int quantity=0;
        try {

            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(GET_INVENTORYQUANTITY);
            preparedStatement.setString(1,itemId);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next())
            {
                quantity=resultSet.getInt("value");
            }

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quantity;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        List<Item> itemList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(GET_ITEMLIST_BY_PRODUCT);
            preparedStatement.setString(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Item item=new Item();
                item.setItemId(resultSet.getString("ITEMID"));
                item.setListPrice(resultSet.getBigDecimal("LISTPRICE"));
                item.setUnitCost(resultSet.getBigDecimal("UNITCOST"));
                item.setSupplierId(resultSet.getInt("supplierId"));
                item.setStatus(resultSet.getString("STATUS"));
                item.setAttribute1(resultSet.getString("attribute1"));
                item.setAttribute2(resultSet.getString("attribute2"));
                item.setAttribute3(resultSet.getString("attribute3"));
                item.setAttribute4(resultSet.getString("attribute4"));
                item.setAttribute5(resultSet.getString("attribute5"));

                Product product=new Product();
                product.setProductId(resultSet.getString("product.productId"));
                product.setName(resultSet.getString("product.name"));
                product.setDescription(resultSet.getString("product.description"));
                product.setCategoryId(resultSet.getString("product.categoryId"));

                item.setProduct(product);
                itemList.add(item);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    @Override
    public Item getItem(String itemId) {
        Item item=null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(GET_ITEM);
            preparedStatement.setString(1,itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                item=new Item();
                item.setItemId(resultSet.getString("ITEMID"));
                item.setListPrice(resultSet.getBigDecimal("LISTPRICE"));
                item.setUnitCost(resultSet.getBigDecimal("UNITCOST"));
                item.setSupplierId(resultSet.getInt("supplierId"));
                item.setStatus(resultSet.getString("STATUS"));
                item.setAttribute1(resultSet.getString("attribute1"));
                item.setAttribute2(resultSet.getString("attribute2"));
                item.setAttribute3(resultSet.getString("attribute3"));
                item.setAttribute4(resultSet.getString("attribute4"));
                item.setAttribute5(resultSet.getString("attribute5"));
                item.setQuantity(resultSet.getInt("QTY"));

                Product product=new Product();
                product.setProductId(resultSet.getString("product.productId"));
                product.setName(resultSet.getString("product.name"));
                product.setDescription(resultSet.getString("product.description"));
                product.setCategoryId(resultSet.getString("product.categoryId"));

                item.setProduct(product);

            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }
}
