package com.metodywytworzenia.controllers;

import com.metodywytworzenia.Main;
import com.metodywytworzenia.models.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CartController implements Initializable {

    private List<Item> items = new ArrayList<>();

    @FXML
    private GridPane grid;

    public CartController() {
    }

    private List<Item> getData() {
        List<Item> items = new ArrayList<>();
        Item item;
        for (int i = 0; i < 5; i++) {
            item = new Item();
            item.setName("iPhone");
            item.setPrice(i);
            item.setDescription("Rozmawiaj ze znajomymi, ściągaj aplikacje lub gry i ciesz się z możliwości oferowanych przez Xiaomi POCO X3 PRO NFC 8/256GB niebieski. Długo godzinna praca smartfona jest zagwarantowana przez baterię o wielkości 5160 mAh. Korzystaj, a gdy wskaźnik będzie bliżej zera, wtedy możesz wykorzystać funkcję szybkiego ładowania o mocy 33 W."+i);
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

                if (column == 3) {
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

    public void redirection() {

    }
}
