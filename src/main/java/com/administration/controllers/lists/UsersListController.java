package com.administration.controllers.lists;

import com.administration.controllers.ItemController;
import com.administration.controllers.ItemNewController;
import com.administration.controllers.UserController;
import com.administration.controllers.UserNewController;
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

public class UsersListController extends Parent implements Initializable {

    @FXML
    private GridPane grid;

    @FXML
    private Button addUserButton;

    private ArrayList<User> users = new ArrayList<>();

    public UsersListController() {

    }

    private void getData() {
        users = User.getUsers();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
        int column = 0;
        int row = 0;
        try {
            if (users != null) {
                for (User user : users) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(Main.class.getResource("/administration/user.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    UserController userController = fxmlLoader.getController();
                    userController.setData(user);

                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorPane, column++, row); //child, obviously column and row
                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addUser() throws IOException {
        Stage stageToUpdate = (Stage) addUserButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/administration/user_new.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        Parent root = loader.load();
        stage.setScene(new Scene(root, 800, 550));
        UserNewController controller = loader.getController();
        controller.setStageToUpdate(stageToUpdate);
        stage.setTitle("New user");
        stage.setResizable(false);//block windows resize
        stage.show();
    }
}
