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
    private int propertyId;
    private int blockNum;
    private int lotNum;
    
    private static int idCounter = 0;
    
    private int floors = 2;
    
    private String status = "For Sale";
    private double propertySize;
    private double contactPrice;
    private Customer owner;

    public Property() {
        this.propertyId = generateId();
        this.blockNum
    }

//    Automatically generates ID when instatiated
    private int generateId() {
        return ++idCounter;
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

    public void showDetails() {
        
    }
    
    public double getContactPrice() {
        return contactPrice;
    }
}
