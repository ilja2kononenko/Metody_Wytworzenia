package com.administration.controllers;

import com.metodywytworzenia.models.Item;
import com.metodywytworzenia.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangeUserController {


    @FXML
    public TextField filed_user_name;
    @FXML
    public TextField filed_user_surname;
    @FXML
    public TextArea filed_user_email;
    @FXML
    private Button submitButton;



    private int id;
    private Stage stageToUpdate;


    public void setUserDetails(User user){
        this.id = user.getId();
        filed_user_name.setText(user.getName());
        filed_user_surname.setText(user.getSurname());
        filed_user_email.setText(user.getEmail());
    }

    public void setStageToUpdate(Stage stage) {
        this.stageToUpdate = stage;
    }

    public void submit_changes(ActionEvent actionEvent) throws SQLException, IOException {
        stageToUpdate.close();
        Connection connection = com.administration.Main.connection;
        String query = "UPDATE users SET name = ?, surname = ?, email = ? WHERE id = ?";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString(1, filed_user_name.getText());
        preparedStmt.setString(2, filed_user_surname.getText());
        preparedStmt.setString(3, filed_user_email.getText());
        preparedStmt.setString(4, Integer.toString(id));
        preparedStmt.executeUpdate();

        openUsersStage();

        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();

        System.out.println("OK");
    }

    private void openUsersStage() throws IOException {
        Parent root = com.administration.Main.loadFXML("users_list");
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("Users");
        stage.setResizable(false);//block windows resize
        stage.show();
    }

}
