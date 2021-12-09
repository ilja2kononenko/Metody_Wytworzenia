package com.administration.controllers;

import com.metodywytworzenia.Main;
import com.metodywytworzenia.models.Item;
import com.metodywytworzenia.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class UserController {
    @FXML
    private Label nameLabel;

    @FXML
    private ImageView image;

    private User user;

    public void setData(User user) {
        this.user = user;
        nameLabel.setText(user.getName());
//        Image imageFromDirectory = new Image(getClass().getResourceAsStream(item.getImageSource()));
//        image.setImage(imageFromDirectory);
    }

    @FXML
    public void prepareUserStage() throws IOException {
        Stage stageToUpdate = (Stage) nameLabel.getScene().getWindow();
        Stage stage = new Stage(StageStyle.DECORATED);
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/administration/change_user.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 800, 550));
        ChangeUserController controller = loader.getController();
        controller.setUserDetails(user);
        controller.setStageToUpdate(stageToUpdate);
        stage.setTitle("Item information");
        stage.setResizable(false);//block windows resize
        stage.show();
    }
}
