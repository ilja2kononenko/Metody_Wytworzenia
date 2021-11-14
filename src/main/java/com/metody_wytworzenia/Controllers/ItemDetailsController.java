package com.metody_wytworzenia.Controllers;

import com.metody_wytworzenia.Models.Item;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemDetailsController implements Initializable {

    @FXML
    private Label itemPrice;

    private Item item;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setItemDetails(Item item){
        this.item = item;
        System.out.println(this.item.getPrice());
    }
}
