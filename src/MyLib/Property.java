/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

/**
 *
 * @author rei doko
 */
public abstract class Property {
    private Block block;
    private static int idCounter = 0;
    private int propertyId;
    private String propertyType;
    private int floors = 2;
    private String status = "For Sale";
    private double propertySize;
    private double contactPrice;

    Property(Block block, String propertyType, double propertySize, double contactPrice) {
        this.block = block;
        this.propertyId = generateId();
        this.propertyType = propertyType;
        this.propertySize = propertySize;
        this.contactPrice = contactPrice;
    }

    private int generateId() {
        return ++idCounter;
    }
    
    public Block getBlock() {
        return block;
    }

    public double getContactPrice() {
        return contactPrice;
    }
}
