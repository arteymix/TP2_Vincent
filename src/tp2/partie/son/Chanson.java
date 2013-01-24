/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.son;

import java.util.Random;

/**
 *
 * @author Vincent
 */
public enum Chanson {

    PETERGUNN,
    JBOND;
    private static Random r = new Random();

    public static Chanson getRandomChanson() {
        switch (r.nextInt(2)) {
            case 0:
                return PETERGUNN;
            case 1:
                return JBOND;
            default:
                return PETERGUNN;
        }
    }
    
    public static Chanson prochaineChanson (Chanson toune) {
        if (toune.equals(Chanson.JBOND)){
            return PETERGUNN;
        } else {
            return JBOND;
        }
    }
}