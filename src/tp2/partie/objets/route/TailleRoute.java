/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.route;

import java.util.Random;

/**
 *
 * @author Vincent
 */
public enum TailleRoute {

    PETIT, //20%
    MOYENNE, //40%
    LARGE, //60%
    TRESLARGE;  //80%

    public static TailleRoute AleatoireRoute() {
        Random r = new Random();
        switch (r.nextInt(4)) {
            case 0:
                return PETIT;
            case 1:
                return MOYENNE;
            case 2:
                return LARGE;
            case 3:
                return TRESLARGE;
            default:
                return PETIT;
        }
    }

    public static TailleRoute AleatoireRoute(RouteDroite rDini) {
        Random r = new Random();
        int z = 0;
        switch (rDini.gettRoute()){
            case PETIT : z = r.nextInt(2);
                break;
            case MOYENNE : z = r.nextInt(3);
                break;
            case LARGE : z = 1 + r.nextInt(3);
                break;
            case TRESLARGE : z = 2 + r.nextInt(2);
                
        }
        
        switch (z) {
            case 0:
                return PETIT;
            case 1:
                return MOYENNE;
            case 2:
                return LARGE;
            case 3:
                return TRESLARGE;
            default:
                return PETIT;
        }
    }
}