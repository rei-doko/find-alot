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

    public Admin(UserManager userManager, String name, String pass) {
        super(name, pass);
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
    
//    public void confirmAgentFee(Payment agentFee) {
//        
//    }
}
