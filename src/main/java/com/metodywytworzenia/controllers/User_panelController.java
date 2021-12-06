package com.metodywytworzenia.controllers;

import com.metodywytworzenia.Connection_Util;
import com.metodywytworzenia.Main;
import com.metodywytworzenia.models.Item;
import com.metodywytworzenia.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class User_panelController extends Parent implements Initializable {

    @FXML
    private GridPane grid;

    @FXML
    private Button logOutButton;

    @FXML
    private TextField fieldUserName;

    @FXML
    private TextField fieldUserSurname;

    @FXML
    private TextField fieldUserEmail;

    private String userName = User.userInstance.getName();
    private String userSurname = User.userInstance.getSurname();
    private String userEmail = User.userInstance.getEmail();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUserDetails();
        ArrayList<Item> items = Item.getAllProducts();

        int column = 0;
        int row = 0;
        try {
            if (items != null) {
                for (Item item : items) {
                    System.out.println(item.name);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(Main.class.getResource("/metodywytworzenia/item.fxml"));

                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(item);

                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorPane, column++, row); //child, obviously column and row
                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            } else {
                System.out.println("No items selected!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserDetails(){
        fieldUserName.setText(userName);
        fieldUserSurname.setText(userSurname);
        fieldUserEmail.setText(userEmail);
    }

    public void logOut () {
        User.logOut();
        windowClose();
    }

    private void windowClose() {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
    }

    public void provideChanges() {
        Connection connection;
        connection = Connection_Util.connect_to_DB("root", "");
        try {
            if(connection != null) {
                String query = "update users set name = ?, surname = ?, email = ? where name = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, fieldUserName.getText());
                preparedStmt.setString(2, fieldUserSurname.getText());
                preparedStmt.setString(3, fieldUserEmail.getText());
                preparedStmt.setString(4, userName);

                // execute the java prepared statement
                preparedStmt.executeUpdate();

                userName = fieldUserName.getText();
                userSurname = fieldUserSurname.getText();
                userEmail = fieldUserEmail.getText();
            }
        } catch (Exception e) {
            System.out.println("Connection not resolved!");
        }
    }
}
