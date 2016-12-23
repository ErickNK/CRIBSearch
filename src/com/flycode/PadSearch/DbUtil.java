package com.flycode.PadSearch;

import java.sql.*;


class DbUtil {

    Connection conn;
    private String login = "test1";
    private String password = "R6a1D8u0";
    private Statement statement;


    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
    boolean connectDb() {
        try {

            conn = DriverManager.getConnection(
                    "jdbc:mysql://85.93.128.108:3306/somebase",
                    login,
                    password);
            return true;

        } catch (SQLException e) {
            System.out.println("Error in connectdb");
//            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error in close");
                    e.printStackTrace();
                }
            }
        }
    }
    */

    ResultSet doSelect(String query) {
        ResultSet resultSet;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://85.93.128.108:3306/somebase",
                    login,
                    password);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            return resultSet;

        } catch (SQLException e) {
            System.out.println("Error in doSelect");
//            e.printStackTrace();
        }

        return null;
    }

    void doUpdate(String query) {
        try {

            conn = DriverManager.getConnection(
                    "jdbc:mysql://85.93.128.108:3306/somebase",
                    login,
                    password);

            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error in updateTable");
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
