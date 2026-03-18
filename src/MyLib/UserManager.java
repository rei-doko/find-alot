/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

import java.util.ArrayList;

/**
 *
 * @author rei doko
 */
public class UserManager {
    private ArrayList<User> userList;
    
    public UserManager() {
        userList = new ArrayList<User>();
    }

    public void addUser(User user) {
        userList.add(user);
    }
    
    public void removeUser(User user) {
        userList.remove(user);
    }
    
    public boolean registerUser(PropertyManager propertyManager, int role, String username, String password) {
        for(User user : userList) {
            if(user.getUsername().equals(username)) {
                return false;
            }
        }
        
        User user = UserFactory.createUser(propertyManager, role, username, password);
        addUser(user);   
        
        return true;
    }
    
    public User verifyUser(String username, String password) {
        for(User user: userList) {
            if((user.getUsername().equals(username)) && (user.getPassword().equals(password))) {
                return user;
            }
        }
        return null;
    }

    public User getUser(String username) { // Fix to search for username instead of userId?
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // User not found
    }

    public ArrayList<User> getAllUsers() {
        return userList;
    }

    public Object getAllAgents() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
