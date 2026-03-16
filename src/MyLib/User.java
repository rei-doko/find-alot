/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

/**
 *
 * @author rei doko
 */
public class User {
    private int userId;
    private String name;
    private String pass;
    
    public User(String name, String pass) {
        this.userId = generateId();
        this.name = name;
        this.pass = pass;
    }

    public int generateId() {
        return (int) (Math.random() * 10000); // Temporarily generates a random ID
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAccountInfo() {
        return "User ID: " + userId + ", Name: " + name;
    }
}
