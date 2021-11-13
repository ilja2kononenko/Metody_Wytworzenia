package com.metody_wytworzenia.Controllers;

import com.metody_wytworzenia.Main;
import com.metody_wytworzenia.Models.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

//    @FXML
//    private ImageView itemImage;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Item> items = new ArrayList<>();

    public PrimaryController() {
    }

    private List<Item> getData() {
        List<Item> items = new ArrayList<>();
        Item item;
        for (int i = 0; i < 20; i++) {
            item = new Item();
            item.setName("iPhone");
            item.setPrice(5000.23);
            //item.setImageSource("@../../cart.png");
            items.add(item);
        }
        return items;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        items.addAll(getData());
        int column = 0;
        int row = 0;
        try {
            for (Item item : items) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(item);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //child, obviously column and row
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void prepareDeliveryStage() throws IOException {
//        stage = new Stage(StageStyle.DECORATED);
//        Parent root = FXMLLoader.load(Main.class.getResource("delivery.fxml"));
//        stage.setScene(new Scene(root, 800, 550));
//        stage.setTitle("Delivery");
//        stage.setResizable(false);//block windows resize
//        stage.show();
        Main.setRoot("delivery");
    }

    public void prepareAccountStage() throws IOException{

    }
}
