package com.gmail.yuramitryahin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    public static Connection getConnection() {
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "2801");
        String url = "jdbc:mysql://localhost:3306/dao";
        try {
            return DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            throw new RuntimeException("Can't establish the connection to database", e);
        }
    }
}
