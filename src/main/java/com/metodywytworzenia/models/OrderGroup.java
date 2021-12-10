package com.metodywytworzenia.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class OrderGroup extends Model{

    int id;
    int user_id;
    Date creation_date;
    double order_PriceSum;
    User customer;

    public static ArrayList<OrderGroup> getOrderGroupsByUserId (int user_id) {

        try{
            ArrayList<OrderGroup> resultsList = new ArrayList<>();
            String sql = "select * from ordergroup where user_id = ?;";

            Connection connection = getConnection();

            if (connection == null) {
                connection = getConnectionAdmin();
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user_id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderGroup orderGroup = new OrderGroup();

                orderGroup.id = resultSet.getInt("id");
                orderGroup.user_id = resultSet.getInt("user_id");
                //orderGroup.creation_date = resultSet.getString("surname");

                resultsList.add(orderGroup);
            }

            return resultsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static ArrayList<OrderGroup> getAllOrderGroups () {

        try{
            ArrayList<OrderGroup> resultsList = new ArrayList<>();
            String sql = "select * from ordergroup;";

            Connection connection = getConnection();

            if (connection == null) {
                connection = getConnectionAdmin();
            }
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderGroup orderGroup = new OrderGroup();

                orderGroup.id = resultSet.getInt("id");
                orderGroup.user_id = resultSet.getInt("user_id");
                //orderGroup.creation_date = resultSet.getString("surname");

                resultsList.add(orderGroup);
            }

            return resultsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static boolean tryCompletePurchase () {

        try{
            ArrayList<Item> cartItems = User.userInstance.cartItems;

            if (cartItems != null && cartItems.size() > 0) {

                String sql = "insert into ordergroup (user_id) values (?);";
                preparedStatement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setInt(1, User.userInstance.id);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Purchase failed!");
                }

                resultSet = preparedStatement.getGeneratedKeys();

                int ordergroup_id = -1;

                if (resultSet.next()) {
                    ordergroup_id = resultSet.getInt(1);

                    for (Item item : cartItems) {
                        sql = "insert into order_item (item_id, ordergroup_id) values (?, ?);";
                        preparedStatement = getConnection().prepareStatement(sql);

                        preparedStatement.setInt(1, item.getId());
                        preparedStatement.setInt(2, ordergroup_id);

                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected == 0) {
                            return false;
                        }
                    }
                } else {
                    throw new SQLException("Purchase failed!");
                }

                return true;


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setOrder_PriceSum(double order_PriceSum) {
        this.order_PriceSum = order_PriceSum;
    }

    public double getOrder_PriceSum() {
        return order_PriceSum;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }
}
