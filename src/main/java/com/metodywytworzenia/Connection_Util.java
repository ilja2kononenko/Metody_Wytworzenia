package com.metodywytworzenia;

import java.sql.*;

public class Connection_Util {
    public static Connection connect_to_DB(String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/mwo_sellprod", user, password);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
