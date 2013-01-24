/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.route;

import java.awt.Rectangle;
import java.util.ArrayList;
import tp2.partie.Partie;
import tp2.partie.objets.voiture.Joueur;

/**
 *
 * @author Vincent
 */
public class RouteTransition implements Route {

    private final static int HAUTEURRECTTRANSITION = 2;
    private int largeurini;
    private int locationXini;
    private int largeurfinal;
    private int locationXfinal;
    private int locationYfinal = 0;
    private static int longueur;
    private ArrayList<Rectangle> mRoute = new ArrayList<Rectangle>();
  
    public RouteTransition(RouteDroite rDini, RouteDroite rDfinal, int decalageY) {

        this.largeurfinal = rDfinal.getLargeur();
        this.largeurini = rDini.getLargeur();
        this.locationXini = rDini.getX();
        this.locationXfinal = rDfinal.getX();
        this.locationYfinal = -1*(longueur+decalageY);

        for (int h = 0; h <= longueur; h = h + HAUTEURRECTTRANSITION) {

            int largeurRect = (largeurini - ((largeurini - largeurfinal) * h / longueur));
            int xRect = (locationXini - ((locationXini - locationXfinal) * h / longueur));
            int yRect = locationYfinal+longueur-h;
            Rectangle rect = new Rectangle(xRect, yRect, largeurRect, HAUTEURRECTTRANSITION);
            mRoute.add(rect);
        }
    }

    @Override
    public void deplacement() {
        locationYfinal = locationYfinal +Joueur.getVitessejoueur();
        for (Rectangle rect : mRoute) {
            rect.y = rect.y + Joueur.getVitessejoueur();
        }
    }

    public static int getLongueur() {
        return longueur;
    }

    @Override
    public int getLocationY() {
        return locationYfinal;
    }

    @Override
    public int getMilieuX() {
        return 500;

    }

    @Override
    public ArrayList<Rectangle> getBounds() {
        return mRoute;
    }

    public static int getHAUTEURRECTTRANSITION() {
        return HAUTEURRECTTRANSITION;
    }

    public static void setLongueur(int longueur) {
        RouteTransition.longueur = longueur;
    }


}
