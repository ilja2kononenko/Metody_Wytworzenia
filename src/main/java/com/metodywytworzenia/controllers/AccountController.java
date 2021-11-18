package com.metodywytworzenia.controllers;

import com.metodywytworzenia.models.Backend_User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable {

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userPassword;

    @FXML
    private TextField userName;

    @FXML
    private TextField userSurname;

    @FXML
    private TextField userPhone;

    @FXML
    private Button continueButton;

    @FXML
    private Button loginButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userName.setVisible(false);
        userSurname.setVisible(false);
        userPhone.setVisible(false);
        continueButton.setVisible(false);
    }

    public void loginToAccount() {
        boolean loginSuccessful = false;
        String[] AdminData = Backend_User.getAdminData();

        if (AdminData != null) {
            String login = AdminData[0];
            String password = AdminData[1];

            System.out.println(userEmail.getText() + " " + userPassword.getText());
            System.out.println(login + " " + password);

            if (userEmail.getText().equals(login) && userPassword.getText().equals(password)) {
                loginSuccessful = true;
            }
        }

        // decision making depending on whether data for login is correct

        if(loginSuccessful) {
            successfulCommunicate();
            windowClose();
        } else {
            unsuccessfulCommunicate();
        }
    }

    public void registerAccount() {
        userName.setVisible(true);
        userSurname.setVisible(true);
        userPhone.setVisible(true);
        continueButton.setVisible(true);
    }

    public void saveUserAccount() {
        //TODO
        //handle save to database
        //what is the next step??
        if(!userEmail.getText().equals("") && !userPassword.getText().equals("") &&
            !userName.getText().equals("") && !userSurname.getText().equals("") && !userPhone.getText().equals("")) {
            windowClose();
        } else {
            unsuccessfulCommunicate();
        }
    }

    private void successfulCommunicate() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message Here...");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }

    private void unsuccessfulCommunicate() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message Here...");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }

    private void windowClose() {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }
}