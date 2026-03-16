/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

/**
 *
 * @author rei doko
 */
public class UserFactory {
    protected User createUser(int role, String username, String password) {
        switch(role){
            case 1:
                return new Customer(username, password);
            case 2:
                return new Agent(username, password);
            case 3:
                return new Admin(username, password);
            default:
                return null;
        }
    }
}
