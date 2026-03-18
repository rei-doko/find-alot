/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

import java.util.ArrayList;

/**
 *
 * 
 */
public class Agent extends User {
    private PropertyManager propertyManager;
    
    public Agent(PropertyManager propertyManager, String name, String pass) {
        super(name, pass);
        this.propertyManager = propertyManager;
    }
    
    public void confirmSale(Property property, Customer customer) {
        
    }
    
    public ArrayList<Block> getAllBlocks() {
        return propertyManager.getAllBlocks();
    }
    
    public Block getBlock(int blockNumber) {
        return propertyManager.getBlock(blockNumber);
    }
    
    public ArrayList<Property> getProperties(int blockNumber) {
        return propertyManager.getProperties(blockNumber);
    }
    
    public Property getProperty(int propertyId) {
        for(Block block : propertyManager.getAllBlocks()) {
            for(Property property : block.getProperties()) {
                if(property.getPropertyId() == propertyId) {
                    return property;
                }
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return getUsername();
    }
}
