/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MyLib;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */

public class PropertyManager {
    // A List of Lists: each inner list is one "Block" of 20 properties
    private ArrayList<ArrayList<Property>> blocks;

    public PropertyManager() {
        blocks = new ArrayList<>();
        generateProperties(5, 20); // 5 blocks, 20 properties each
    }

    private void generateProperties(int totalBlocks, int propsPerBlock) {
        for (int b = 1; b <= totalBlocks; b++) {
            ArrayList<Property> currentBlock = new ArrayList<>();
            
            for (int l = 1; l <= propsPerBlock; l++) {
                // Factory creates the property and handles the ID/Type logic
                Property newProp = PropertyFactory.createProperty(b, l);
                currentBlock.add(newProp);
            }
            
            blocks.add(currentBlock);
        }
    }

    public void showAllProperties() {
        for (int i = 0; i < blocks.size(); i++) {
            System.out.println("\n--- DISPLAYING BLOCK " + (i + 1) + " ---");
            for (Property p : blocks.get(i)) {
                System.out.print("ID: " + p.getPropertyId() + " | ");
                p.showDetails();
            }
        }
    }
}
