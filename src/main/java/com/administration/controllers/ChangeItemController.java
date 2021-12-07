package com.administration.controllers;

import com.metodywytworzenia.models.Item;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChangeItemController {

    @FXML
    public TextField filed_product_title;
    @FXML
    public TextField field_product_price;
    @FXML
    public TextArea field_product_description;
    @FXML
    private Button submitButton;



    private int id;
    private Stage stageToUpdate;


    public void setItemDetails(Item item){
        this.id = item.id;
        filed_product_title.setText(item.getName());
        field_product_price.setText(Double.toString(item.getPrice()));
        field_product_description.setText(item.getDescription());
    }

    public void setStageToUpdate(Stage stage) {
        this.stageToUpdate = stage;
    }

    public void submit_changes(ActionEvent actionEvent) throws SQLException, IOException {
        stageToUpdate.close();
        Connection connection = com.administration.Main.connection;
        String query = "UPDATE products SET title = ?, price = ?, description = ? WHERE id = ?";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString(1, filed_product_title.getText());
        preparedStmt.setString(2, field_product_price.getText());
        preparedStmt.setString(3, field_product_description.getText());
        preparedStmt.setString(4, Integer.toString(id));
        preparedStmt.executeUpdate();

        openItemsStage();

        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
        
        System.out.println("OK");
    }

    private void openItemsStage() throws IOException {
        Parent root = com.administration.Main.loadFXML("item_list");
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("Items");
        stage.setResizable(false);//block windows resize
        stage.show();
    }

}
