package com.metodywytworzenia.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Item extends Model{

    public int id;
    public String name;
    //public String imageSource;
    public int price;
    public String description;

    public static ArrayList<Item> getProductsByOrderGroupId(int id) {
        try{
            ArrayList<Item> resultsList = new ArrayList<>();
            String sql = "select * from order_item where ordergroup_id=?;";

            Connection connection = getConnection();

            if (connection == null) {
                connection = getConnectionAdmin();
            }

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {

                resultsList = new ArrayList<>();

                while (resultSet.next()) {

                    int item_id = resultSet.getInt("item_id");

                    sql = "select * from products where id=?;";

                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, item_id);

                    ResultSet resultSet2 = preparedStatement.executeQuery();

                    while (resultSet2.next()) {
                        Item item = new Item();

                        item.id = resultSet2.getInt("id");
                        item.name = resultSet2.getString("title");
                        item.price = resultSet2.getInt("price");
                        item.description = resultSet2.getString("description");

                        resultsList.add(item);
                    }
                }

                return resultsList;
            } else {
                return null;
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getImageSource() {
//        return imageSource;
//    }
//
//    public void setImageSource(String imageSource) {
//        this.imageSource = imageSource;
//    }

    public static ArrayList<Item> getAllProducts () {

            try{
                ArrayList<Item> resultsList = new ArrayList<>();
                String sql = "select * from products;";

                preparedStatement = getConnection().prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                setItemPanel(resultsList);

                return resultsList;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;

    }

    public static ArrayList<Item> getAllProductsAdmin() {

        try{
            ArrayList<Item> resultsList = new ArrayList<>();
            String sql = "select * from products;";
            preparedStatement = getConnectionAdmin().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            setItemPanel(resultsList);

            return resultsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    private static void setItemPanel(ArrayList<Item> resultsList) throws SQLException {
        while (resultSet.next()) {
            Item item = new Item();

            item.id = resultSet.getInt("id");
            item.name = resultSet.getString("title");
            item.price = resultSet.getInt("price");
            item.description = resultSet.getString("description");

            resultsList.add(item);
        }
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
