/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyApp;

import MyLib.Admin;
import MyLib.UserManager;
import MyLib.PropertyManager;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager(); // Create userManager
        PropertyManager propertyManager = new PropertyManager(); // Create propertyManager
        
        // Admin account creation
        Admin adminAccount = new Admin(userManager, "admin", "password");
        userManager.addUser(adminAccount);
        
        // Application GUI
        java.awt.EventQueue.invokeLater(() -> {
            new Authentication(userManager, propertyManager).setVisible(true);
        });
    }
}   