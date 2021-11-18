package com.metodywytworzenia.models;

import java.sql.SQLException;
import java.util.ArrayList;

public class Item extends Model{

    private int id;
    private String name;
    private String imageSource;
    private int price;
    private String description;

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

                while (resultSet.next()) {
                    Item item = new Item();

                    item.id = resultSet.getInt("id");
                    item.name = resultSet.getString("title");
                    item.price = resultSet.getInt("price");
                    item.description = resultSet.getString("description");

                    resultsList.add(item);
                }

                return resultsList;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;

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
