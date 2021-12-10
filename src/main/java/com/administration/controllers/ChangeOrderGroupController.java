package com.administration.controllers;

import com.metodywytworzenia.models.Item;
import com.metodywytworzenia.models.OrderGroup;
import com.metodywytworzenia.models.User;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChangeOrderGroupController {

    public int id;
    public Label client_data;
    public Label total_price;
    public GridPane grid;
    private Stage stageToUpdate;

    private ArrayList<Item> orders;

    public void setOrderGroupDetails(OrderGroup orderGroup){
        this.id = orderGroup.getId();

        orders = Item.getProductsByOrderGroupId(orderGroup.getId());
        int row = 2;

        client_data.setText(orderGroup.getCustomer().getName() + " " + orderGroup.getCustomer().getSurname() + ", " + orderGroup.getCustomer().getEmail());
        total_price.setText(orderGroup.getOrder_PriceSum() + "");

        if (orders != null) {
            for (Item order : orders) {

                HBox actions = new HBox();

                Button see_details = new Button("Details");
                see_details.setOnAction(e -> removeOrderFromOrderGroup());

                actions.getChildren().addAll(see_details);

                grid.addRow(row,
                        new Label(order.getName() + ""),
                        new Label(order.getPrice() + ""),
                        see_details
                );

                row++;
            }
        }
    }

    public void setStageToUpdate(Stage stage) {
        this.stageToUpdate = stage;
    }

    private void openUsersStage() throws IOException {
        Parent root = com.administration.Main.loadFXML("users_list");
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("Users");
        stage.setResizable(false);//block windows resize
        stage.show();
    }

    public void removeOrderGroup(ActionEvent actionEvent) {
    }

    public void removeOrderFromOrderGroup () {

    }
}
