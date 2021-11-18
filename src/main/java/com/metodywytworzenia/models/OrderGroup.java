package com.metodywytworzenia.models;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class OrderGroup extends Model{

    int id;
    int user_id;
    Date creation_date;

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

}
