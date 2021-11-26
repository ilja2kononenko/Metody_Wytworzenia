package com.metodywytworzenia.models;


import java.sql.SQLException;
import java.util.ArrayList;

public class Backend_User extends Model{

    public static String[] getAdminData() {
        try{
            String sql = "select * from backend_users;";
            preparedStatement = getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            String[] results = new String[2];
            results[0] = resultSet.getString(1);
            results[1] = resultSet.getString(2);

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
