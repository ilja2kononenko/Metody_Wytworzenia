package com.metody_wytworzenia.Models;

public class Item {

    private String name;
    private String imageSource;
    private double price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
