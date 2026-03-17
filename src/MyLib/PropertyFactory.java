/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MyLib;

/**
 *
 * @author Admin
 */
public class PropertyFactory {
    private static int type = 0;
    
    protected static Property createProperty(int blockNum, int lotNum) {
        type++;
        int select = type % 3;

        int floors = 2;
        double size = 150.00;//placeholder-temporary
        double price = 4000000.00;//placeholder-temporary

        switch(select){
            case 1:
                return new Detached(blockNum, lotNum, floors, size, price, 2, 50.0);
            case 2:
                return new SemiDetached(blockNum, lotNum, floors, size, price, "Left", 25.0);
            case 0:
                return new TownHouse(blockNum, lotNum, floors, size, price, 2);
            default:
                return null;
        }
    }
}
