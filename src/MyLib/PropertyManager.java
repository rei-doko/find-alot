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
    private Block block;
    private ArrayList<Block> blocks;

    public PropertyManager() {
        blocks = new ArrayList<>();
        generateProperties(5, 20);
    }

    private void generateProperties(int totalBlocks, int propsPerBlock) {
        for (int b = 1; b <= totalBlocks; b++) {
            block = new Block(b);
            
            for (int l = 1; l <= propsPerBlock; l++) {
                Property newProp = PropertyFactory.createProperty(b, l);
                block.addProperty(newProp);
            }
            blocks.add(block);
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
    
    public ArrayList<Property> filterProperties(Integer blockNum, Double minPrice, Double maxPrice){
        ArrayList<Property> result = new ArrayList<>();
        for(Block block : blocks){
            if (blockNum != null && block.getBlockNumber() != blockNum){
                continue;
            }
            for (Property property : block.getProperties()) {
                Double price = property.getContactPrice();
                
                if (price == null) {
                    continue;
                }
                    
                if (minPrice != null && price < minPrice) {
                    continue;
                }
                    
                if (maxPrice != null && price > maxPrice) {
                    continue;
                }
                result.add(property);
            }
        }
    return result;
    }
}
