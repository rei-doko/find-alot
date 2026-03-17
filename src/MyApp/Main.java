/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyApp;

import MyLib.Admin;
import MyLib.Session;
import MyLib.User;
import java.util.Scanner;
import MyLib.UserManager;
import MyLib.PropertyManager;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager(); // Create userManager
        Scanner scan = new Scanner(System.in);
        
        // Admin account creation
        Admin adminAccount = new Admin(userManager, "admin", "password");
        userManager.addUser(adminAccount);
        
        // Application GUI
        java.awt.EventQueue.invokeLater(() -> {
            new Authentication(userManager).setVisible(true);
        });


        System.out.println("=== Initializing Real Estate Database ===");

        PropertyManager manager = new PropertyManager();
        manager.showAllProperties();

        System.out.println("\n=== Initialization Complete: 100 Properties Generated ===");
    }
}   