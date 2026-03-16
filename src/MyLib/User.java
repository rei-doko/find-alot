/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

/**
 *
 * @author rei doko
 */
public abstract class User {
    private int userId;
    private String username;
    private String password;
    
    public User(String username, String password) {
        this.userId = setUserId(generateId());
        this.username = username;
        this.password = password;
    }

    public int generateId() {
        return (int) (Math.random() * 10000); // Temporarily generates a random ID
    }

    public int getUserId() {
        return userId;
    }

    public int setUserId(int userId) {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountInfo() {
        return "User ID: " + userId + ", Username: " + username;
    }
}
