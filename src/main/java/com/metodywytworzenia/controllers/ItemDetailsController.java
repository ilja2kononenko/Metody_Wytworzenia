package com.metodywytworzenia.controllers;

import com.metodywytworzenia.models.Item;
import com.metodywytworzenia.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.metodywytworzenia.models.Model.getConnectionAdmin;

public class ItemDetailsController implements Initializable {

    Item currentItem;

    @FXML
    private Label itemDescription;

    @FXML
    private Label itemName;

    @FXML
    private Label itemPrice;

    @FXML
    private Button primaryButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setItemDetails(Item item){
        currentItem = item;
        itemName.setText(item.getName());
        itemPrice.setText(Double.toString(item.getPrice()));
        itemDescription.setWrapText(true);
        itemDescription.setText(item.getDescription());
        if(isAdminUser()) {
            primaryButton.setVisible(false);
        } else {
            deleteButton.setVisible(false);
            editButton.setVisible(false);
        }
    }

    private boolean isAdminUser() {
        return getConnectionAdmin() != null;
    }

    public void addItemToCart() {
        if (User.isLogged) {
            User.userInstance.addItemToCart(currentItem);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Product added!");
            alert.setHeaderText("Product was successfully added to cart!");
            alert.setContentText("Please go to cart to finish your purchase!");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    windowClose();
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Authorization error");
            alert.setHeaderText("You are not logged in!");
            alert.setContentText("Please log in to buy something!");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    windowClose();
                }
            });
        }


    }

    private void windowClose() {
        Stage stage = (Stage) primaryButton.getScene().getWindow();
        stage.close();
    }

    public void deleteItem() throws SQLException {
        deleteFromDb();
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        stage.close();
    }

    private void deleteFromDb() throws SQLException {
        Connection connection = com.administration.Main.connection;
        String query = "DELETE FROM products WHERE title ='" + itemName.getText() + "'";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.executeUpdate();
    }

    public void editItem(ActionEvent actionEvent) {
        //TODO: add possibility to edit item
    }
}
