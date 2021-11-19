package com.metodywytworzenia.models;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User extends Model{

    int id;
    String name;
    String surname;
    int money;
    String email;
    String password;
    ArrayList<Item> cartItems;
    public static User userInstance;
    public static boolean isLogged = false;

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
            String sql = "insert into users (name, surname, email, password) values (?, ?, ?, ?);";
            preparedStatement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.name);
            preparedStatement.setString(2, user.surname);
            preparedStatement.setString(3, user.email);
            preparedStatement.setString(4, user.password);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Registration unsuccessful!");
            }

            if (rowsAffected == 1) {
                resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {

                    int user_id = resultSet.getInt(1);

                    System.out.println("User id equals: " + user_id);

                    ArrayList<User> users = getUsers();

                    if (users != null) {
                        for (User possibleUser : users) {
                            if (possibleUser.id == user_id) {
                                User.logIn(possibleUser);
                                break;
                            }
                        }
                    }

                    if (User.isLogged) {
                        return true;
                    } else {
                        return false;
                    }

                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void logIn(User user) {
        userInstance = user;
        isLogged = true;
        userInstance.cartItems = new ArrayList<>();
    }

    public static void logOut() {
        userInstance = null;
        isLogged = false;
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

    public ArrayList<Item> getCartItems() {
        return cartItems;
    }

    public void addItemToCart (Item item) {
        cartItems.add(item);
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

    public void setMoney(int money) {
        this.money = money;
    }

    public static void setIsLogged(boolean isLogged) {
        User.isLogged = isLogged;
    }

    public static void setUserInstance(User userInstance) {
        User.userInstance = userInstance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void clearCart() {
        userInstance.cartItems = new ArrayList<>();
    }
}
