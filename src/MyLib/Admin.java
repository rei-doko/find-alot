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
public class Admin extends User {
    private UserManager userManager;

    public Admin(UserManager userManager, String username, String password) {
        super(username, password);
        this.userManager = userManager;
    }

    public void addUser(User user) {
        userManager.addUser(user);
    }
    public void removeUser(User user) {
        userManager.removeUser(user);
    }
    
    public ArrayList<User> getAllUsers() {
        return userManager.getAllUsers();
    }
    
    public User getUser(String username) {
        return userManager.getUser(username);
    }
    
//    public void confirmAgentFee(Payment agentFee) {
//        
//    }
}
