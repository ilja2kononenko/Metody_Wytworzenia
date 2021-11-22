package com.administration.controllers;

import com.metodywytworzenia.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PrimaryController {

    public void showItems() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/com.administration/item_list.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("Items");
        stage.setResizable(false);//block windows resize
        stage.show();
    }

    public void showUsers() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/com.administration/users_list.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("Users");
        stage.setResizable(false);//block windows resize
        stage.show();
    }

    public void showOrderGroups() {

    }
}
