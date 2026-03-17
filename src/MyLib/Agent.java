/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

/**
 *
 * @author rei doko
 */
public class Agent extends User {
    private PropertyManager propertyManager;
    
    public Agent(PropertyManager propertyManager, String name, String pass) {
        super(name, pass);
        this.propertyManager = propertyManager;
    }
    
    public void confirmSale(Property property, Customer customer) {
        
    }
}
