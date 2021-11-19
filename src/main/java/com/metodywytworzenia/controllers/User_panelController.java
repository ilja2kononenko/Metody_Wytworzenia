package com.metodywytworzenia.controllers;

import com.metodywytworzenia.Main;
import com.metodywytworzenia.models.Item;
import com.metodywytworzenia.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class User_panelController extends Parent implements Initializable {

    @FXML
    private GridPane grid;

    @FXML
    private Button logOutButton;

    private ArrayList<Item> items = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        items = Item.getAllProducts();

        int column = 0;
        int row = 0;
        try {
            if (items != null) {
                for (Item item : items) {
                    System.out.println(item.name);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(Main.class.getResource("item.fxml"));

                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(item);

                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorPane, column++, row); //child, obviously column and row
                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            } else {
                System.out.println("No items selected!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOut () {
        User.logOut();
        windowClose();
    }

    private void windowClose() {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
    }
}
