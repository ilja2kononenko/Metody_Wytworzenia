package com.metodywytworzenia.models;

import com.metodywytworzenia.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class Model {

    public static PreparedStatement preparedStatement;
    public static ResultSet resultSet;

    public static Connection getConnection() {
        System.out.println(Main.connection);
        return Main.connection;
    }

    public static Connection getConnectionAdmin() {
        System.out.println(com.administration.Main.connection);
        return com.administration.Main.connection;
    }
}
