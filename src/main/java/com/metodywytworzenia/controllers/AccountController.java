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
                    break;
                }
            }
        }

        // decision making depending on whether data for login is correct

        if(loginSuccessful) {
            System.out.println("Login successful");
            successfulCommunicate();
            windowClose();
        } else {
            System.out.println("Error! Login unsuccessful!");
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

        User user = new User();

        user.setName(userName.getText());
        user.setSurname(userSurname.getText());
        user.setEmail(userEmail.getText());
        user.setPassword(userPassword.getText());

        if(User.tryRegister(user)) {
            System.out.println("Register successful!");
            windowClose();
        } else {
            System.out.println("Error! Register unsuccessful!");
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
