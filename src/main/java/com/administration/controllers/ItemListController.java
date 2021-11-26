package com.administration.controllers;

import com.metodywytworzenia.Main;
import com.metodywytworzenia.models.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemListController extends Parent implements Initializable {

    @FXML
    private GridPane grid;

    private ArrayList<Item> items = new ArrayList<>();

    public ItemListController() {

    }

    private void getData() {
        items = Item.getAllProductsAdmin();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
        int column = 0;
        int row = 0;
        try {
            if (items != null) {
                for (Item item : items) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(Main.class.getResource("/com.administration/item.fxml"));
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addItem() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/com.administration/item_new.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("New item");
        stage.setResizable(false);//block windows resize
        stage.show();
    }
}
