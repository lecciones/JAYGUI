/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;
import java.sql.Connection;
import java.sql.Statement;
/**
 *
 * @author Angie
 */
public class table {
   public static void main(String[] args) {

        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "firstname TEXT NOT NULL," +
                "lastname TEXT NOT NULL," +
                "email TEXT NOT NULL UNIQUE," +
                "username TEXT NOT NULL UNIQUE," +
                "password TEXT NOT NULL" +
                ");";

        try (Connection conn = DBcon.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Table created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
}
