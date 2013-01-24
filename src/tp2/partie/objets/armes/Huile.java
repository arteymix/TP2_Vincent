/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.armes;

import java.awt.Rectangle;
import java.util.ArrayList;
import tp2.partie.objets.autres.ObjGenerique;
import tp2.partie.objets.voiture.Joueur;

/**
 *
 * @author Vincent
 */
public class Huile extends ObjGenerique {

    /**
     *
     * @param pJoueur
     */
    public Huile(Joueur pJoueur) {
        super((int) pJoueur.getLARGEUR(), Joueur.getVitessejoueur(),(int) pJoueur.getX(), (int) (pJoueur.getY() + pJoueur.getLONGUEUR()), 0, 0);

    }
}
