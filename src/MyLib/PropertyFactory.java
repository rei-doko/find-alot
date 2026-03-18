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
    public static int variant = 0;
    
    public static Property createProperty(int blockNum, int lotNum) {
        variant = lotNum % 10;
        
        switch(blockNum){
            case 1:
            case 2:
                return createTownHouse(blockNum, lotNum);
            case 3:
            case 4:
                return createSemiDetached(blockNum, lotNum);
            case 5:
                return createDetached(blockNum, lotNum);
            default:
                return null;
        }
    }
    
    public static Property createTownHouse (int blockNum, int lotNum){
        switch(variant){
                case 1:
                case 2:
                case 3:
                case 4:
                    return new TownHouse(blockNum, lotNum, 2, 60.0, 3400000.0, 3);
                case 5:
                case 6:
                case 7:
                    return new TownHouse(blockNum, lotNum, 2, 75.0, 3800000.0, 3);
                case 8:
                case 9:
                case 0:
                    return new TownHouse(blockNum, lotNum, 3, 80.0, 4000000.0, 3);
                default:
                    return null;
            }
    }
    
    public static Property createSemiDetached (int blockNum, int lotNum){
        switch(variant){
                case 1:
                case 2:
                case 3:
                case 4:
                    return new SemiDetached(blockNum, lotNum, 2, 60.0, 4000000.0, "Left", 25.0);
                case 5:
                case 6:
                case 7:
                    return new SemiDetached(blockNum, lotNum, 2, 75.0, 4200000.0, "Left", 30.0);
                case 8:
                case 9:
                case 0:
                    return new SemiDetached(blockNum, lotNum, 3, 80.0, 4500000.0, "Left", 35.0);
                default:
                    return null;
            }
    }
    
    public static Property createDetached (int blockNum, int lotNum){
        switch(variant){
            case 1:
            case 2:
            case 3:
            case 4:
                return new Detached(blockNum, lotNum, 2, 60.0, 4300000.0, 1, 10.0);
            case 5:
            case 6:
            case 7:
                return new Detached(blockNum, lotNum, 2, 75.0, 4500000.0, 1, 15.0);
            case 8:
            case 9:
            case 0:
                return new Detached(blockNum, lotNum, 3, 80.0, 4800000.0, 1, 15.0);
            default:
                return null;
        }
    }
}
