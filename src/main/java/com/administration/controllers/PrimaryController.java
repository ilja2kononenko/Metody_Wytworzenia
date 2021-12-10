package com.administration.controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PrimaryController {

    public void showItems() throws IOException {
        Parent root = com.administration.Main.loadFXML("item_list");
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("Items");
        stage.setResizable(false);//block windows resize
        stage.show();
    }

    public void showUsers() throws IOException {
        Parent root = com.administration.Main.loadFXML("users_list");
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("Users");
        stage.setResizable(false);//block windows resize
        stage.show();
    }

    public void showOrderGroups() throws IOException{
        Parent root = com.administration.Main.loadFXML("orderGroups_list");
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("Order groups");
        stage.setResizable(false);//block windows resize
        stage.show();
    }
}
