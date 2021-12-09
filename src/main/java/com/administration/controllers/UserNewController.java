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

public class UserNewController {

    @FXML
    private TextField userName;

    @FXML
    private TextField userSurname;

    @FXML
    private TextField userEmial;

    @FXML
    private Button addButton;

    private Stage stageToUpdate;

    public void addToDB() throws SQLException, IOException {
        stageToUpdate.close();

        Connection connection = com.administration.Main.connection;
        String query = "INSERT INTO users VALUES (DEFAULT, ?, ?, 1000, ?, 123)";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString(1, userName.getText());
        preparedStmt.setString(2, userSurname.getText());
        preparedStmt.setString(3, userEmial.getText());

        // execute the java prepared statement
        preparedStmt.executeUpdate();

        openUserList();

        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
        System.out.println("OK");
    }

    private void openUserList() throws IOException {
        Parent root = com.administration.Main.loadFXML("users_list");
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("Users");
        stage.setResizable(false);//block windows resize
        stage.show();
    }

    public void setStageToUpdate(Stage stage) {
        this.stageToUpdate = stage;
    }
}

