package com.administration.controllers.lists;

import com.administration.controllers.ItemController;
import com.metodywytworzenia.Main;
import com.metodywytworzenia.models.Item;
import com.metodywytworzenia.models.OrderGroup;
import com.metodywytworzenia.models.User;
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

public class OrderGroupsListController extends Parent implements Initializable {

    @FXML
    private GridPane grid;

    private ArrayList<OrderGroup> orderGroups = new ArrayList<>();

    public OrderGroupsListController() {

    }

    private void getData() {
        orderGroups = OrderGroup.getAllOrderGroups();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
        int row = 2;

        if (orderGroups != null) {
            for (OrderGroup orderGroup : orderGroups) {

                User user = User.getUserById(orderGroup.getUser_id());

                ArrayList<Item> products = Item.getProductsByOrderGroupId(orderGroup.getId());

                double sum = 0;

                for (Item item : products) {
                    sum += item.getPrice();
                }

                grid.addRow(row,
                        new Label(orderGroup.getId() + ""),
                        new Label(user.getName() + " " + user.getSurname() + ""),
                        new Label(user.getEmail() + ""),
                        new Label(sum + "")
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
}
