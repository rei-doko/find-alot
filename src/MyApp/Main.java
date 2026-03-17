/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyApp;

import MyLib.Session;
import MyLib.User;
import java.util.Scanner;
import MyLib.UserManager;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager(); // Create userManager
        Scanner scan = new Scanner(System.in);
        
//        // Admin account creation
//        userManager.registerUser(3, "admin", "password");
//        User adminAccount = userManager.getUser("admin");
        
        // Application GUI
        java.awt.EventQueue.invokeLater(() -> {
            new Authentication(userManager).setVisible(true);
        });
        
        
        
        
        
        
        
//        // REGISTER SYSTEM PROTOTYPE
//        System.out.println("REGISTER");
//        
//        System.out.print("""
//                         Choose account type:
//                         [1] Customer
//                         [2] Agent
//                         [3] Admin
//                         
//                         Enter your choice: 
//                         """);
//        int role = scan.nextInt();
//        scan.nextLine();
//        
//        System.out.print("Enter username: ");
//        String username = scan.nextLine();
//        System.out.print("Enter password: ");
//        String password = scan.nextLine();
//        
//        
//        boolean success = userManager.registerUser(role, username, password);
//        if(success) {
//            User user = userManager.findUser(username);
//            System.out.println(user.getUsername() + " sucessfully registered.");
//        }
//        else {
//            System.out.println("User already exists.");
//        }
//
//        // LOGIN SYSTEM PROTOTYPE
//        System.out.println("LOGIN");
//        
//        System.out.print("Enter username: ");
//        username = scan.nextLine();
//        System.out.print("Enter password: ");
//        password = scan.nextLine();
//        
//        Session.login(username, password, Manager);
//        
//        System.out.println(Session.getCurrentUser().getUsername() + " is current user.");
//        System.out.println(Session.isLoggedIn());
//        Session.logout();
//        System.out.println(Session.getCurrentUser().getUsername() + " is current user.");
//        System.out.println(Session.isLoggedIn());
    }
}   