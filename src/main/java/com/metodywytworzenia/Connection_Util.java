package com.metodywytworzenia;

import java.sql.*;

public class Connection_Util {
    public static Connection connect_to_DB(String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mwo_sellprod", user, password);
            return connection;
        }
        catch (Exception e)
        {
            e.getMessage();
            return null;
        }
    }
}
