package com.metodywytworzenia.controllers;

import com.metodywytworzenia.Main;
import com.metodywytworzenia.models.Item;
import com.metodywytworzenia.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PrimaryController extends Parent implements Initializable {

    @FXML
    private Label ItemNameLabel;

    @FXML
    private Label ItemPriceLabel;

    @FXML
    private VBox chosenItemCard;

    @FXML
    private ImageView itemImage;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private ArrayList<Item> items = new ArrayList<>();

    public PrimaryController() {
    }

    private ArrayList<Item> getData() {
        items = Item.getAllProducts();

        return items;
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void prepareCartStage() throws IOException {
        Stage stage = new Stage(StageStyle.DECORATED);
        Parent root = FXMLLoader.load(Main.class.getResource("cart.fxml"));
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("Cart");
        stage.setResizable(false);//block windows resize
        stage.show();
    }

    @FXML
    public void prepareAccountStage() throws IOException{
        Stage stage = new Stage(StageStyle.DECORATED);

        String fxmlString = "account.fxml";

        if (User.isLogged) {
            fxmlString = "user_panel.fxml";
        }

        Parent root = FXMLLoader.load(Main.class.getResource(fxmlString));

        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("Account");
        stage.setResizable(false);//block windows resize
        stage.show();
    }
}
