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
    private User user;
    
    public UserManager() {
        userList = new ArrayList<User>();
    }

    public void addUser(User user) {
        userList.add(user);
    }
    
    public void registerUser(int role, String username, String password) {
        user = UserFactory.createUser(role, username, password);
        
        addUser(user);
    }

    public void removeUser(User user) {
        userList.remove(user);
    }

    public User findUser(int userId) {
        for (User user : userList) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null; // User not found
    }

    public ArrayList<User> getAllUsers() {
        return userList;
    }
}
