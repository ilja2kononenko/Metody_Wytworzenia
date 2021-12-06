package com.administration.controllers.lists;

import com.administration.controllers.ItemController;
import com.metodywytworzenia.Main;
import com.metodywytworzenia.models.Item;
import com.metodywytworzenia.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UsersListController extends Parent implements Initializable {

    @FXML
    private GridPane grid;

    public UsersListController() {

    }

    private ArrayList<User> users = new ArrayList<>();

    private void getData() {
        users = User.getUsers();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
        int row = 2;

        if (users != null) {
            for (User user : users) {

                grid.addRow(row,
                        new Label(user.getId() + ""),
                        new Label(user.getName()),
                        new Label(user.getSurname()),
                        new Label(user.getMoney() + ""),
                        new Label(user.getEmail()),
                        new Label(user.getPassword())
                );

                row++;
            }
        }
    }

    public void addItem() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/administration/item_new.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("New item");
        stage.setResizable(false);//block windows resize
        stage.show();
    }

    public void addUser(ActionEvent actionEvent) {

    }
}
