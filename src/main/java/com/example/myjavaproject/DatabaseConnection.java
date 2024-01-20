package com.example.myjavaproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection databaseLink;
    public Connection getConnection() {
        String databaseUser = "root";
        String databasePass = "Newlife12!";
        String url = "jdbc:mysql://localhost:3306/student";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePass);


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }



}
