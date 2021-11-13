package com.metody_wytworzenia;

import com.metody_wytworzenia.Models.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemListController implements Initializable {

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

    public ItemListController() {
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
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));

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
}
