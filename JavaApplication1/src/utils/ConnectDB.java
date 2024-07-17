/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.*;

/**
 *
 * @author sangh
 */
public class ConnectDB {

    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyCafe;encrypt=true;trustServerCertificate=true;";
    private static String username = "sa";
    private static String password = "Password.1";

    //Nạp driver
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, username, password);
    }

    public static PreparedStatement preparedStatement(String sql, Object... args) throws SQLException {
        Connection conn = DriverManager.getConnection(dburl, username, password);
        PreparedStatement pstm = null;
        if (sql.trim().startsWith("{")) {
            pstm = conn.prepareCall(sql);
        } else {
            pstm = conn.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pstm.setObject(i + 1, args[i]);
        }
        return pstm;
    }

    public static void executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement pstm = preparedStatement(sql, args);
            try {
                pstm.executeUpdate();
            } finally {
                pstm.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement pstm = preparedStatement(sql, args);
            return pstm.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testConnection() {
        try (Connection conn = DriverManager.getConnection(dburl, username, password)) {
            if (conn != null) {
                System.out.println("Connect successful");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testConnection();
    }

    public static Connection openConnection() throws SQLException {

        try {
            // Load the JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load SQL Server JDBC Driver", e);
        }

        // Create and return a connection to the database
        return DriverManager.getConnection(dburl, username, password);
    }
}
