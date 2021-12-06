package com.administration.controllers;

import com.metodywytworzenia.models.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ChangeItemController {

    @FXML
    public TextField filed_product_title;
    @FXML
    public TextField field_product_price;
    @FXML
    public TextArea field_product_description;

    public void setItemDetails(Item item){
        filed_product_title.setText(item.getName());
        field_product_price.setText(Double.toString(item.getPrice()));
        field_product_description.setText(item.getDescription());
    }

    public void submit_changes(ActionEvent actionEvent) {

    }
}
