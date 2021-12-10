package com.administration.controllers;

import com.metodywytworzenia.Main;
import com.metodywytworzenia.models.Item;
import com.metodywytworzenia.models.OrderGroup;
import com.metodywytworzenia.models.Order_item;
import com.metodywytworzenia.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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
import java.sql.Statement;
import java.util.ArrayList;

public class ChangeOrderGroupController {

    public int id;
    public Label client_data;
    public Label total_price;
    public GridPane grid;
    public Button removeOrderGroupButton;
    private Stage stageToUpdate;
    private OrderGroup orderGroup;


    private ArrayList<Order_item> orders;
    private ArrayList<Item> products;

    public void setOrderGroupDetails(OrderGroup orderGroup){
        this.orderGroup = orderGroup;
        this.id = orderGroup.getId();

        orders = Order_item.getOrdersByOrderGroupId(orderGroup.getId());
        products = Item.getProductsByOrderGroupId(orderGroup.getId());
        int row = 2;

        client_data.setText(orderGroup.getCustomer().getName() + " " + orderGroup.getCustomer().getSurname() + ", " + orderGroup.getCustomer().getEmail());
        total_price.setText(orderGroup.getOrder_PriceSum() + "");

        if (products != null) {
            for (Item item : products) {

                Order_item current_order_item = null;

                for (Order_item order_item : orders) {
                    if (order_item.getItem_id() == item.getId()) {
                        current_order_item = order_item;
                        break;
                    }
                }

                if (current_order_item != null) {
                    Order_item finalCurrent_order_item = current_order_item;

                    HBox actions = new HBox();

                    Button remove_item_from_orderGroup = new Button("Remove");
                    remove_item_from_orderGroup.setOnAction(e -> removeOrderFromOrderGroup(finalCurrent_order_item.getId()));
                    actions.getChildren().addAll(remove_item_from_orderGroup);

                    Button duplicate_item_from_orderGroup = new Button("Duplicate");
                    duplicate_item_from_orderGroup.setOnAction(e -> duplicateOrderFromOrderGroup(item.getId()));
                    actions.getChildren().addAll(duplicate_item_from_orderGroup);

                    grid.addRow(row,
                            new Label(item.getName() + ""),
                            new Label(item.getPrice() + ""),
                            actions
                    );

                    row++;
                }
            }
        }
    }

    public void setStageToUpdate(Stage stage) {
        this.stageToUpdate = stage;
    }

    public void removeOrderGroup() throws SQLException, IOException {
        stageToUpdate.close();

        Connection connection = com.administration.Main.connection;
        String query = "DELETE from ordergroup WHERE id = ?";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setInt(1, id);
        preparedStmt.executeUpdate();

        query = "DELETE from order_item WHERE ordergroup_id = ?";
        preparedStmt = connection.prepareStatement(query);
        preparedStmt.setInt(1, id);
        preparedStmt.executeUpdate();

        Stage stage = (Stage) removeOrderGroupButton.getScene().getWindow();
        stage.close();
    }

    private void duplicateOrderFromOrderGroup(int order_id) {
        try {
            stageToUpdate.close();

            Connection connection = com.administration.Main.connection;
            String query = "insert into order_item (item_id, ordergroup_id) values (?, ?);";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, order_id);
            preparedStmt.setInt(2, id);
            preparedStmt.executeUpdate();

            Stage stage = (Stage) removeOrderGroupButton.getScene().getWindow();
            stage.close();
            openOrderGroupsListStage();
            openOrderGroupChangeStage();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeOrderFromOrderGroup (int orderId)  {
        stageToUpdate.close();

        try{
            Connection connection = com.administration.Main.connection;
            String query = "DELETE from order_item WHERE id=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            System.out.println("Order id to remove: " + orderId);
            preparedStmt.setInt(1, orderId);
            preparedStmt.executeUpdate();

            ArrayList<Item> orders_in_orderGroup = Item.getProductsByOrderGroupId(id);
            if (orders_in_orderGroup == null || orders_in_orderGroup.size() == 0) {
                try {
                    removeOrderGroup();
                    Stage stage = (Stage) removeOrderGroupButton.getScene().getWindow();
                    stage.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    openOrderGroupChangeStage();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

        try {
            openOrderGroupsListStage();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    private void openOrderGroupChangeStage() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/administration/change_orderGroup.fxml"));
            Parent root = loader.load();

            ChangeOrderGroupController controller = loader.getController();
            controller.setOrderGroupDetails(orderGroup);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(root, 800, 550));
            stage.setTitle("OrderGroup details");
            stage.setResizable(false);//block windows resize
            stage.show();
        } catch (IOException e) {
            System.out.println("Error! resource not found!");
        }
    }

    private void openOrderGroupsListStage() throws IOException {
        Parent root = com.administration.Main.loadFXML("orderGroups_list");
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("Order groups");
        stage.setResizable(false);//block windows resize
        stage.show();
    }

}
