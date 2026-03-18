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
    protected static User createUser(PropertyManager propertyManager, int role, String username, String password) {
        switch(role){
            case 1:
                return new Customer(propertyManager, username, password);
            case 2:
                return new Agent(propertyManager, username, password);
            default:
                return null;
        }
    }
}
