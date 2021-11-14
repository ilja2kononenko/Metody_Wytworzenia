package com.metody_wytworzenia.Controllers;

import com.metody_wytworzenia.Models.Item;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemDetailsController implements Initializable {


    @FXML
    private Label itemDescription;

    @FXML
    private Label itemName;

    @FXML
    private Label itemPrice;

    @FXML
    private SplitPane splitPane;


    @FXML
    private Button primaryButton;

    private Item item;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setItemDetails(Item item){
        this.item = item;
        itemName.setText(item.getName());
        itemPrice.setText(Double.toString(item.getPrice()));
        itemDescription.setWrapText(true);
        itemDescription.setText(item.getDescription());
    }
}
