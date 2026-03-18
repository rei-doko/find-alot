/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MyLib;

import java.util.ArrayList;

/**
 *
 * o
 */

public class PropertyManager {
    // A List of Lists: each inner list is one "Block" of 20 properties
    private Block block;
    private ArrayList<Block> blocks;

    public PropertyManager() {
        blocks = new ArrayList<>();
        generateProperties(5, 20); // 5 blocks, 20 properties each
    }

    private void generateProperties(int totalBlocks, int propsPerBlock) {
        for (int b = 1; b <= totalBlocks; b++) {
            block = new Block(b); // Creates individual blocks
            
            for (int l = 1; l <= propsPerBlock; l++) { // Creates individual properties
                // Factory creates the property and handles the ID/Type logic
                Property newProp = PropertyFactory.createProperty(b, l);
                block.addProperty(newProp);
            }
            blocks.add(block); // Adds individual blocks to ArrayList<Block> blocks
        }
    }
    
    public Block getBlock(int blockNumber) {
        for(Block block : blocks) {
            if(block.getBlockNumber() == blockNumber) {
                return block;
            }
        }
        return null;
    }
    
    public ArrayList<Block> getAllBlocks() {
        return blocks;
    }
    
    public ArrayList<Property> getProperties(int blockNumber) {
        Block currentBlock = getBlock(blockNumber);
        if(currentBlock == null) {
            return null;
        }
        return currentBlock.getProperties();
    }

    public void buyProperty(int propertyId, Customer buyer) {
        for(Block block : blocks) {
            for(Property property : block.getProperties()) {
                if(property.getPropertyId() == propertyId) {
                    property.updateStatus("Buy");
                    property.setOwner(buyer);
                }
            }
        }
    }
    
    public void bookProperty(int propertyId, Customer buyer) {
        for(Block block: blocks) {
            for(Property property : block.getProperties()) {
                if(property.getPropertyId() == propertyId) {
                    property.updateStatus("Book");
                }
            }
        }
    }

    //filter

    public ArrayList<Property> filterProperties(Integer blockNum, Double minPrice, Double maxPrice){
        ArrayList<Property> result = new ArrayList<>();
        for(Block block : blocks){
            if (blockNum != null && block.getBlockNumber() != blockNum){
                continue;
            }
            for (Property property : block.getProperties()) {
                if (price == null)
                    continue;
                if (minPrice != null && property.getContactPrice() < minPrice)
                    continue;
                if (maxPrice != null && property.getContactPrice() > maxPrice)
                    continue;

                result.add(property);
        }
    }
    return result;
}
