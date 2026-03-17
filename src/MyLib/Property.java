package MyLib;

/**
 * 
 */
public abstract class Property {
    private Block block;
    private int blockNum; 
    private int lotNum;
    
    private static int idCounter = 0; 
    private int propertyId;
    
    private int floors = 2;
    private String status = "For Sale";
    private double propertySize;
    private double contactPrice;
    private Customer owner;

    // Fixed the broken constructor and added parameters
    public Property(Block block, int lotNum, double propertySize, double contactPrice) {
        this.block = block;
        this.blockNum = block.getBlockNumber();
        this.lotNum = lotNum;
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

    public abstract void showDetails();
    
    public double getContactPrice() { return contactPrice; }
    public String getStatus() { return status; }
    public int getPropertyId() { return propertyId; }
    public int getBlockNum() { return blockNum; }
    public int getLotNum() { return lotNum; }
}