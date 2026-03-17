package MyLib;

/**
 * 
 */
public abstract class Property {
    private int blockNum; 
    private int propertyNum;
    
    private static int idCounter = 0; 
    private int propertyId;
    
    private int floors = 2;
    private String status = "For Sale";
    private double propertySize;
    private double contactPrice;
    private Customer owner;

    // Fixed the broken constructor and added parameters
    public Property(int blockNum, int propertyNum, int floors, double propertySize, double contactPrice) {
        this.blockNum = blockNum;
        this.propertyNum = propertyNum;
        this.floors = floors;
        this.propertySize = propertySize;
        this.contactPrice = contactPrice;
        this.propertyId = generateId();
    }

    // Automatically generates ID when instantiated
    private int generateId() {
        return ++idCounter;
    }
    
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
    
    public void setOwner(Customer buyer) {
        owner = buyer;
    }

    public abstract void showDetails(); // Not needed
    
    public double getContactPrice() { 
        return contactPrice; 
    }
    
    public String getStatus() { 
        return status; 
    }
    
    public int getPropertyId() { 
        return propertyId; 
    }
    
    public int getBlockNum() { 
        return blockNum; 
    }
    
    public int getPropertyNum() { 
        return propertyNum; 
    }
    
    public Customer getOwner() {
        return owner;
    }
    
    public double getPropertySize() {
        return propertySize;
    }
    
    public int getFloors() {
        return floors;
    }
}