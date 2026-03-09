/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

/**
 *
 * 
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

//    Automatically generates ID when instatiated
    private int generateId() {
        return ++idCounter;
    }
    
    public Block getBlock() {
        return block;
    }
    
//    Incomplete
    public void updateStatus(String action) {
        if(action == "Book") {
            this.status = "Book";
        }
        else if(action == "Buy") {
            this.status = "Sold";
        }
        else {
            this.status = "For Sale";
        }
    }

    public double getContactPrice() {
        return contactPrice;
    }
}
