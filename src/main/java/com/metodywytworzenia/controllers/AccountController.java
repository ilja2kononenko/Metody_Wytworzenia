package com.metodywytworzenia.controllers;

import com.metodywytworzenia.models.Backend_User;
import com.metodywytworzenia.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
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

        ArrayList<User> users = User.getUsers();
        boolean loginSuccessful = false;

        if (users != null) {
            for (User user : users) {
                if (userEmail.getText().equals(user.getEmail()) && userPassword.getText().equals(user.getPassword())) {
                    loginSuccessful = true;
                    User.logIn(user);
                    break;
                }
            }
        }

        // decision making depending on whether data for login is correct

        if(loginSuccessful) {
            successfulCommunicate("Login successful!", "You successfully logged in!", "Now you can make purchases!");
            windowClose();
        } else {
            unsuccessfulCommunicate("Error! Login was not successful!", "Something went wrong!", "Please check if all fields are okay and try again!");
        }
    }

    public void registerAccount() {
        userName.setVisible(true);
        userSurname.setVisible(true);
        userPhone.setVisible(true);
        continueButton.setVisible(true);
    }

    public void saveUserAccount() {

        User user = new User();

        user.setName(userName.getText());
        user.setSurname(userSurname.getText());
        user.setEmail(userEmail.getText());
        user.setPassword(userPassword.getText());

        if(User.tryRegister(user)) {
            successfulCommunicate("Register successful!", "Your account was successfully created!", "Now you can make purchases!");
            windowClose();
        } else {
            unsuccessfulCommunicate("Error! Registration was not successful!", "Something went wrong!", "Please check if all fields are okay and try again!");
        }
    }

    private void successfulCommunicate(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                windowClose();
            }
        });
    }

    private void unsuccessfulCommunicate(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
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
