package com.metodywytworzenia.controllers;

import com.metodywytworzenia.Main;
import com.metodywytworzenia.models.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView image;

    private Item item;

    public void setData(Item item) {
        this.item = item;
        nameLabel.setText(item.getName());
        priceLabel.setText(Double.toString(item.getPrice()));
//        Image imageFromDirectory = new Image(getClass().getResourceAsStream(item.getImageSource()));
//        image.setImage(imageFromDirectory);
    }

    @FXML
    public void prepareItemStage() throws IOException {
        Stage stage = new Stage(StageStyle.DECORATED);
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/metodywytworzenia/item_details.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 800, 550));
        ItemDetailsController controller = loader.getController();
        controller.setItemDetails(item);
        stage.setTitle("Item information");
        stage.setResizable(false);//block windows resize
        stage.show();
    }
}
