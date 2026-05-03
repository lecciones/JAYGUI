/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author Lester
 */

public class userSession {
    
    private static userSession instance;
    private String id;
    private String fname;
    private String role;

    // Private constructor prevents creating multiple sessions
    private userSession() {}

    public static userSession getInstance() {
        if (instance == null) {
            instance = new userSession();
        }
        return instance;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFname() { return fname; }
    public void setFname(String fname) { this.fname = fname; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}