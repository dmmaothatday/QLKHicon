/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLKHModel;
import QLKHController.*;
/**
 *
 * @author 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseModel {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String urlDB = "jdbc:sqlserver://localhost:1433; databaseName=quan_ly_kho_hang; encrypt=false";
            String userName = "sa";
            String passwordUser = "123456";
            conn = DriverManager.getConnection(urlDB, userName, passwordUser);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
}