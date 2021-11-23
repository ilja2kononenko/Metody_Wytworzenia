package com.administration.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemNewController {

    @FXML
    private TextField itemName;

    @FXML
    private TextField itemPrice;

    @FXML
    private TextField itemDescription;

    @FXML
    private Button addButton;

    public void addToDB() throws SQLException {
        Connection connection = com.administration.Main.connection;
        String query = "INSERT INTO products VALUES (DEFAULT, ?, ?, ?)";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString(1, itemName.getText());
        preparedStmt.setString(2, itemPrice.getText());
        preparedStmt.setString(3, itemDescription.getText());

        // execute the java prepared statement
        preparedStmt.executeUpdate();


        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
        System.out.println("OK");
    }

}
