package com.metody_wytworzenia.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable {

    @FXML
    private Label userEmail;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userEmail.setVisible(true);
    }
}
