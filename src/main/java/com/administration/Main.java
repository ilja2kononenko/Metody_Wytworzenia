package com.administration;

import com.metodywytworzenia.Connection_Util;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {

    public static Connection connection;
    public static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {

        connection = Connection_Util.connect_to_DB("admin", "admin");
        String sql = "select * from users;";

        try {
            PreparedStatement stmt = null;
            if (connection != null) {
                stmt = connection.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery();

                resultSet.next();

                System.out.println(resultSet.getString("name"));
            } else {
                System.out.println("Connection wasn't established!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        scene = new Scene(loadFXML("primary_admin"), 800, 550);
        stage.setTitle("Administration Panel");
        stage.setResizable(false);//block windows resize
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com.administration/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
