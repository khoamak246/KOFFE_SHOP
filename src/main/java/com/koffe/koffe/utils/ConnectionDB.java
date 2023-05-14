package com.koffe.koffe.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/koffe_shop";
    private static final String USER = "root";
    private static final String PASSWORD = "ngochan3124";

    public static Connection getConnection() {
        Connection conn;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void closeConnection(Connection conn, CallableStatement callableStatement) {
        try {
            if (conn != null)
                conn.close();
            if (callableStatement != null)
                callableStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
