package com.metodywytworzenia.models;

import java.sql.SQLException;
import java.util.ArrayList;

public class User extends Model{

    int id;
    String name;
    String surname;
    int money;
    String email;
    String password;

    public static ArrayList<User> getUsers() {
        try{
            ArrayList<User> resultsList = new ArrayList<>();
            String sql = "select * from users;";
            preparedStatement = getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                user.id = resultSet.getInt("id");
                user.name = resultSet.getString("name");
                user.surname = resultSet.getString("surname");
                user.money = resultSet.getInt("money");
                user.email = resultSet.getString("email");
                user.password = resultSet.getString("password");

                resultsList.add(user);
            }

            return resultsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean tryRegister (User user) {

        try{
            ArrayList<User> resultsList = new ArrayList<>();
            String sql = "insert into users (name, surname, email, password) values (?, ?, ?, ?);";
            preparedStatement = getConnection().prepareStatement(sql);

            preparedStatement.setString(1, user.name);
            preparedStatement.setString(2, user.surname);
            preparedStatement.setString(3, user.email);
            preparedStatement.setString(4, user.password);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
