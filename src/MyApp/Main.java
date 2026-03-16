/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyApp;

import MyLib.User;
import MyLib.UserFactory;
import java.util.Scanner;
import MyLib.UserManager;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager(); // Create userManager
        Scanner scan = new Scanner(System.in);
//        UserFactory userFactory = new UserFactory();

         
        System.out.println("REGISTER");
        
        System.out.print("""
                         Choose account type:
                         [1] Customer
                         [2] Agent
                         [3] Admin
                         
                         Enter your choice: 
                         """);
        int role = scan.nextInt();
        scan.nextLine();
        
        System.out.print("Enter username: ");
        String username = scan.nextLine();
        System.out.print("Enter password: ");
        String password = scan.nextLine();
        
        userManager.registerUser(role, username, password);
        
        // // Placeholder for register system
        // User user1 = new User("Jane Doe", "RAT");

        // userManager.addUser(user1);
        // ArrayList<User> users = userManager.getAllUsers();
        // for(User user : users) {
        //     System.out.println(user.getAccountInfo());
        // }

        // for(User user: users) {
        //     System.out.println(userManager.findUser(user.getUserId()).getAccountInfo());
        // }
    }
}
