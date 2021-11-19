package com.metodywytworzenia.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Order_item extends Model{

    int id;
    int item_id;
    int ordergroup_id;

    public static ArrayList<Item> getAllOrder_Items (int user_id) {

        try{
            ArrayList<Item> resultsList = new ArrayList<>();
            String sql = "select * from ordergroup where user_id = ?;";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, User.userInstance.id);
            resultSet = preparedStatement.executeQuery();

            ArrayList<Integer> productIds = new ArrayList<>();

            if (resultSet.next()) {

                sql = "select * from order_item where ordergroup_id = ?;";

                preparedStatement = getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, resultSet.getInt("id"));

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    productIds.add(resultSet.getInt("item_id"));
                }

            }

            for (Integer productId : productIds) {
                sql = "select * from products where id = ?;";
                preparedStatement = getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, productId);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    Item item = new Item();

                    item.id = resultSet.getInt("id");
                    item.name = resultSet.getString("title");
                    item.price = resultSet.getInt("price");
                    item.description = resultSet.getString("description");

                    resultsList.add(item);
                }
            }

            return resultsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}
