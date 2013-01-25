/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.voiture;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * Méchante limousine.
 * 
 * @author Usager
 */
public class Limousine extends Voiture implements Mechant {

    private static int PVinitial = 100;
    private static int vmax = Joueur.getVitesseMaxJoueur() * 5 / 8;
    private final static int accelerationmax = 1;
    private final static int deplacementlateral = 2;
    private final static int LARGEUR = 31;
    private final static int LONGUEUR = 63;
    private static Image img;

    public Limousine(int positionX, int positionY) {
        super(PVinitial, vmax, deplacementlateral, accelerationmax, LARGEUR, LONGUEUR, positionX, positionY, 0, 20);
        img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/limo.png"));
    }

    public Image getImg() {
        return img;
    }
    
    // Possède un tireur qui peut tirer à 360
}