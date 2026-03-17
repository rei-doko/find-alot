/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

/**
 * 
 */

//Not yet done btw
public abstract class Property {
    private Block block;
    private static int idCounter = 0; 
    private int propertyId;
    private String propertyType;
    private int floors = 2;
    private String status = "For Sale";
    private double propertySize;
    private double contactPrice;

    // Added public so subclasses and factories can access it
    public Property(Block block, String propertyType, double propertySize, double contactPrice) {
        this.block = block;
        this.propertyId = generateId();
        this.propertyType = propertyType;
        this.propertySize = propertySize;
        this.contactPrice = contactPrice;
    }

    // Automatically generates ID when instantiated
    private int generateId() {
        return ++idCounter;
    }
    
  //For the GUI
    public Block getBlock() { return block; }
    public int getPropertyId() { return propertyId; }
    public String getPropertyType() { return propertyType; }
    public int getFloors() { return floors; }
    public String getStatus() { return status; }
    public double getPropertySize() { return propertySize; }
    public double getContactPrice() { return contactPrice; }

    // added and fixed logic for updating status
    public void updateStatus(String action) {

        if(action.equalsIgnoreCase("Book")) {
            this.status = "Booked"; 
        }
        else if(action.equalsIgnoreCase("Buy")) {
            this.status = "Sold";
        }
        else {
            this.status = "For Sale";
        }
    }

    public abstract void showDetails(); 
}