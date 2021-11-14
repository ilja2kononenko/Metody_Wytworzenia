package com.metody_wytworzenia.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userName.setVisible(false);
        userSurname.setVisible(false);
        userPhone.setVisible(false);
        continueButton.setVisible(false);
    }

    public void loginToAccount() {
        //handle login to account??

    }

    public void registerAccount() {
        userName.setVisible(true);
        userSurname.setVisible(true);
        userPhone.setVisible(true);
        continueButton.setVisible(true);
    }
}
