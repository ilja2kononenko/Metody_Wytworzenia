package com.administration.controllers.lists;

import com.administration.controllers.ChangeItemController;
import com.administration.controllers.ChangeOrderGroupController;
import com.administration.controllers.ItemController;
import com.metodywytworzenia.Main;
import com.metodywytworzenia.models.Item;
import com.metodywytworzenia.models.OrderGroup;
import com.metodywytworzenia.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    public Button addItemButton;
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

                if (products != null) {
                    double sum = 0;

                    for (Item item : products) {
                        sum += item.getPrice();
                    }

                    orderGroup.setOrder_PriceSum(sum);
                    orderGroup.setCustomer(user);

                    Button see_details = new Button("Details");
                    see_details.setOnAction(e -> seeOrderGroupDetails(orderGroup));

                    grid.addRow(row,
                            new Label(orderGroup.getId() + ""),
                            new Label(user.getName() + " " + user.getSurname() + ""),
                            new Label(user.getEmail() + ""),
                            new Label(sum + ""),
                            see_details
                    );

                    row++;
                }
            }
        }
    }

    public void seeOrderGroupDetails (OrderGroup orderGroup) {
        try {
            Stage stageToUpdate = (Stage) addItemButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/administration/change_orderGroup.fxml"));
            Parent root = loader.load();

            ChangeOrderGroupController controller = loader.getController();
            controller.setOrderGroupDetails(orderGroup);
            controller.setStageToUpdate(stageToUpdate);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(root, 800, 550));
            stage.setTitle("OrderGroup details");
            stage.setResizable(false);//block windows resize
            stage.show();
        } catch (IOException e) {
            System.out.println("Error! resource not found!");
        }

    }
}
