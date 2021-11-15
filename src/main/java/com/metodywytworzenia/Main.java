package com.metodywytworzenia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Main extends Application {

    public static Connection connection;
    public static Scene scene;

    @Override
    public void start(Stage stage) throws Exception{

        // for testing connection and showing how it works

        System.out.println("Test");

        connection = Connection_Util.connect_to_DB("root", "");
        String sql = "select * from users;";

        try {
            PreparedStatement stmt = null;
            if (connection != null) {
                stmt = connection.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery();

                resultSet.next();

                System.out.println(resultSet.getString("id"));
            } else {
                System.out.println("Connection wasn't established!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // end of demo of Database connection

        scene = new Scene(loadFXML("primary"), 800, 550);
        stage.setTitle("Prodsell");
        stage.setResizable(false);//block windows resize
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}