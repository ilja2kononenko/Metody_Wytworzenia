package com.metodywytworzenia.models;

import java.sql.Connection;
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

    public static ArrayList<Order_item> getOrdersByOrderGroupId(int id) {
        try{
            ArrayList<Order_item> resultsList2 = new ArrayList<>();
            String sql = "select * from order_item where ordergroup_id=?;";

            Connection connection = getConnection();

            if (connection == null) {
                connection = getConnectionAdmin();
            }

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {

                while (resultSet.next()) {
                    Order_item order_item = new Order_item();
                    order_item.setId(resultSet.getInt("id"));
                    order_item.setOrdergroup_id(resultSet.getInt("ordergroup_id"));
                    order_item.setItem_id(resultSet.getInt("item_id"));

                    resultsList2.add(order_item);
                }

                return resultsList2;
            } else {
                return null;
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public int getItem_id() {
        return item_id;
    }

    public int getOrdergroup_id() {
        return ordergroup_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public void setOrdergroup_id(int ordergroup_id) {
        this.ordergroup_id = ordergroup_id;
    }
}
