package MyLib;


public class TownHouse extends Property {
    private int sharedWalls;

    public TownHouse(int blockNum, int lotNum, int floors, double propertySize, double contactPrice, int sharedWalls) {
        super(blockNum, lotNum, floors, propertySize, contactPrice);
        this.sharedWalls = sharedWalls;
    }

    @Override
    public void showDetails() { 
        System.out.println("TownHouse with " + sharedWalls + " shared walls."); 
    }
}
