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
public class Block {
    private static int blockCounter = 0;
    private int blockNumber;
    private ArrayList<Property> properties;
    
    Block() {
        this.blockNumber = blockCounter;
    }
    
//    Counter for automatically adding to new block when lot reaches 20 [Subject to Change]
    public void addBlockCounter() {
        blockCounter++;
    }
    
//    Adds properties to the ArrayList properties
    public void addProperty(Property property) {
        if (property != null) {
            this.properties.add(property);
        }
    }
    
//    Returns the ArrayList properties
    public ArrayList<Property> getProperties() {
        return properties;
    }
}
