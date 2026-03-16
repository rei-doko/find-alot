/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyApp;

import MyLib.User;
import MyLib.UserManager;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        UserManager userManager = new UserManager(); // Create userManager

        // Placeholder for register system
        User user1 = new User("Jane Doe", "RAT");

        userManager.addUser(user1);
        ArrayList<User> users = userManager.getAllUsers();
        for(User user : users) {
            System.out.println(user.getAccountInfo());
        }

        for(User user: users) {
            System.out.println(userManager.findUser(user.getUserId()).getAccountInfo());
        }
    }
}
