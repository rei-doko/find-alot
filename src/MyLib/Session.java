/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

/**
 *
 * @author rei doko
 */
public class Session {
    private static User currentUser;
    
    public static boolean login(String username, String password, UserManager manager) {
        User user = manager.verifyUser(username, password);
        
        if(user != null) {
            currentUser = user;
            return true;
        }
        return false;
    }
    
    public static void logout() {
        currentUser = null;
    }
    
    public static User getCurrentUser() {
        return currentUser;
    }
    
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
