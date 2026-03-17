package MyLib;
//Not final
public class SemiDetached extends Property {
    private String sharedWallsSide;
    private double yardSize;
    private boolean yard;

    public SemiDetached(int blockNum, int lotNum, int floors, double propertySize, double contactPrice, String sharedWallsSide, double yardSize) {
        super(blockNum, lotNum, floors, propertySize, contactPrice);
        this.sharedWallsSide = sharedWallsSide;
        this.yardSize = yardSize;
        this.yard = yardSize > 0;
    }

    @Override
    public void showDetails() { 
        System.out.println("Semi-Detached House. Shared on the " + sharedWallsSide + " side."); 
    }
}