/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.voiture;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Usager
 */
public class Civil extends Voiture {

    private final static int vitessemax = Joueur.getVitessejoueur() / 2;
    private final static int PVinitial = 50;
    private final static int accelerationmax = 1;
    private final static int deplacementlateral = 3;
    private final static int LONGUEUR = 50;
    private final static int LARGEUR = 25;
    private Image img;

    public Civil(int positionX, int positionY) {
        super(PVinitial, vitessemax, deplacementlateral, accelerationmax, LARGEUR, LONGUEUR, positionX, positionY, 0, 20);
        img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/charCivil.png"));
    }

    @Override
    public Image getImg() {
        return img;
    }
}
