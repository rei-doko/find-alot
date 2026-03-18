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
        int select = type % 10;

        int floors = 2;
        double price = 100.0;
        double size = 100.0;
        
        switch(blockNum){
            case 1:
            case 2:
                switch(select){
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        floors = 2;
                        price = 3400000.0;
                        size = 60.0;
                        return new TownHouse(blockNum, lotNum, floors, size, price, 3);
                    case 5:
                    case 6:
                    case 7:
                        floors = 2;
                        price = 3800000.0;
                        size = 75.0;
                        return new TownHouse(blockNum, lotNum, floors, size, price, 3);
                    case 8:
                    case 9:
                    case 0:
                        floors = 3;
                        price = 4000000.0;
                        size = 80.0;
                        return new TownHouse(blockNum, lotNum, floors, size, price, 3);
                    default:
                        return null;
                }
            case 3:
            case 4:
                switch(select){
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        floors = 2;
                        price = 4000000.0;
                        size = 60.0;
                        return new SemiDetached(blockNum, lotNum, floors, size, price, "Left", 50.0);
                    case 5:
                    case 6:
                    case 7:
                        floors = 2;
                        price = 4200000.0;
                        size = 75.0;
                        return new SemiDetached(blockNum, lotNum, floors, size, price, "Left", 50.0);
                    case 8:
                    case 9:
                    case 0:
                        floors = 3;
                        price = 4500000.0;
                        size = 80.0;
                        return new SemiDetached(blockNum, lotNum, floors, size, price, "Left", 50.0);
                    default:
                        return null;
                }
            case 5:
                switch(select){
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        floors = 2;
                        price = 4300000.0;
                        size = 60.0;
                        return new Detached(blockNum, lotNum, floors, size, price, 2, 50.0);
                    case 5:
                    case 6:
                    case 7:
                        floors = 2;
                        price = 4500000.0;
                        size = 75.0;
                        return new Detached(blockNum, lotNum, floors, size, price, 2, 50.0);
                    case 8:
                    case 9:
                    case 0:
                        floors = 3;
                        price = 4800000.0;
                        size = 80.0;
                        return new Detached(blockNum, lotNum, floors, size, price, 2, 50.0);
                    default:
                        return null;
                }
            default:
                return null;
        }
    }
}
