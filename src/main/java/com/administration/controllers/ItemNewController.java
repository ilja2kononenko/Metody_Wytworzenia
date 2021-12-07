package com.administration.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemNewController {

    @FXML
    private TextField itemName;

    @FXML
    private TextField itemPrice;

    @FXML
    private TextField itemDescription;

    @FXML
    private Button addButton;

    private Stage stageToUpdate;

    public void addToDB() throws SQLException, IOException {
        stageToUpdate.close();

        Connection connection = com.administration.Main.connection;
        String query = "INSERT INTO products VALUES (DEFAULT, ?, ?, ?)";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString(1, itemName.getText());
        preparedStmt.setString(2, itemPrice.getText());
        preparedStmt.setString(3, itemDescription.getText());

        // execute the java prepared statement
        preparedStmt.executeUpdate();

        openItemList();

        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
        System.out.println("OK");
    }

    private void openItemList() throws IOException {
        Parent root = com.administration.Main.loadFXML("item_list");
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("Items");
        stage.setResizable(false);//block windows resize
        stage.show();
    }

    public void setStageToUpdate(Stage stage) {
        this.stageToUpdate = stage;
    }

}
