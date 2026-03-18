package MyLib;
public class Detached extends Property {
    private int hasGarage;
    private double yardSize;
    private boolean yard;

    public Detached(int blockNum, int lotNum, int floors, double propertySize, double contactPrice, int hasGarage, double yardSize) {
        super(blockNum, lotNum, floors, propertySize, contactPrice);
        this.hasGarage = hasGarage;
        this.yardSize = yardSize;
        this.yard = yardSize > 0;
    }

    @Override
    public void showDetails() { 
        System.out.println("Detached House. Garage capacity: " + hasGarage + " cars. Yard: " + yard); 
    }
}