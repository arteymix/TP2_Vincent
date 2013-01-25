/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.autres;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Usager
 */
public class Ravitaillement extends ObjGenerique {

    private static final int LARGEUR = 50;
    private static final int LONGUEUR = 48;
    private static final int VX = 0;
    private static final int VY = 0;
    private static Image imgHuile;
    private static Image imgBalles;
    private boolean huile = false;

    public Ravitaillement(int positionX, int positionY, boolean huile) {
        super(LARGEUR, LONGUEUR, positionX, positionY, VX, VY);
        this.huile = huile;
        imgHuile = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/boxHuile.png"));
        imgBalles = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/boxBalle.png"));
    }

    public Image getImg() {
        if (huile) {
            return imgHuile;
        } else {
            return imgBalles;
        }
    }

    public boolean isHuile() {
        return huile;
    }
}
