package com.metodywytworzenia.controllers;

import com.metodywytworzenia.Main;
import com.metodywytworzenia.models.Item;
import com.metodywytworzenia.models.OrderGroup;
import com.metodywytworzenia.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CartController implements Initializable {

    private List<Item> items = new ArrayList<>();

    @FXML
    private GridPane grid;

    @FXML
    private Button orderButton;

    public CartController() {
    }

    private ArrayList<Item> getData() {
        if (User.isLogged) {

            ArrayList<Item> itemsInCart = User.userInstance.getCartItems();

            for (Item item : itemsInCart) {
                item.setDescription("Rozmawiaj ze znajomymi, ściągaj aplikacje lub gry i ciesz się z możliwości oferowanych przez Xiaomi POCO X3 PRO NFC 8/256GB niebieski. Długo godzinna praca smartfona jest zagwarantowana przez baterię o wielkości 5160 mAh. Korzystaj, a gdy wskaźnik będzie bliżej zera, wtedy możesz wykorzystać funkcję szybkiego ładowania o mocy 33 W.");
                //item.setImageSource("@../../cart.png");
                //items.add(item);
            }
            return itemsInCart;
        } else {
            return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Item> items = getData();

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

    public void completePurchase(ActionEvent actionEvent) {

        if (User.isLogged) {
            boolean purchaseSuccessful = OrderGroup.tryCompletePurchase();

            if (purchaseSuccessful) {

                User.userInstance.clearCart();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Purchase successful!");
                alert.setHeaderText("Thank you for buying our products!");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        windowClose();
                    }
                });
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Purchase error");
                alert.setHeaderText("Something went wrong!");
                alert.setContentText("Please contact a developer!");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        windowClose();
                    }
                });
            }
        }

    }

    private void windowClose() {
        Stage stage = (Stage) orderButton.getScene().getWindow();
        stage.close();
    }
}
