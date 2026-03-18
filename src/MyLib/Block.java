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
public class Block {
    private int blockNumber;
    private ArrayList<Property> properties;
    
    public Block(int blockNumber) {
        this.blockNumber = blockNumber;
        this.properties = new ArrayList<>();
    }
    
    public int getBlockNumber() {
        return blockNumber;
    }
    
    public ArrayList<Property> getProperties() {
        return properties;
    }
    
    public void addProperty(Property property) {
        properties.add(property);
    }
    
    public Property getProperty(int propertyId) {
        for(Property property : properties) {
            if(property.getPropertyId() == propertyId) {
                return property;
            }
        }
        return null;
    }
}
