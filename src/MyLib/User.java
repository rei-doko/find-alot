/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

/**
 *
 * @author rei doko
 */
public abstract class User {
    private int userId;
    private String name;
    private String pass;
    
    public User(String name, String pass) {
        this.userId = generateId();
        this.name = name;
        this.pass = pass;
    }
    
    private int generateId() {
//        public static int idNum;
//        
//        idNum = rand();
        
        return idNum;
    }

    public void login() {

    }
    
    public void logout() {
        
    }
    
    public void generateReport() {
        
    }
}
