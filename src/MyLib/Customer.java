/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

import java.util.ArrayList;

/**
 *
 * @author rei doko
 */
public class Customer extends User {
    private PropertyManager propertyManager;
    
    public Customer(PropertyManager propertyManager, String username, String password) {
        super(username, password);
        this.propertyManager = propertyManager;
    }
    
    public ArrayList<Block> getAllBlocks() {
        return propertyManager.getAllBlocks();
    }
    
    public Block getBlock(int blockNumber) {
        return propertyManager.getBlock(blockNumber);
    }
    
    public ArrayList<Property> getProperties() {
        return propertyManager.getProperties();
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
    
//    public List<Property> searchProperty(int blockNum, double propertySize, double contactPrize) {
//        return
//    }
//    
//    public void contactAgent(Agent agent, String message) {
//        
//    }
}
