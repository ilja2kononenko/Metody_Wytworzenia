package com.metody_wytworzenia;

import com.metody_wytworzenia.Models.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    //private ImageView image;

    private Item item;

    public void setData(Item item) {
        this.item = item;
        nameLabel.setText(item.getName());
        priceLabel.setText(Double.toString(item.getPrice()));
        //Image imageFromDirectory = new Image(getClass().getResourceAsStream(item.getImageSource()));
        //image.setImage(imageFromDirectory);
      }
}
