/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.route;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import tp2.graphique.FramePartie;
import tp2.partie.Partie;
import tp2.partie.objets.voiture.Joueur;

/**
 *
 * @author Vincent
 */
public class RouteDroite implements Route {

    private static int pnllargeur;
    private static int longueur;
    private int x = 0;
    private int largeur = 0;
    private int locationY = 0;
    private TailleRoute tRoute;
    private ArrayList<Rectangle> CONTOUR = new ArrayList<Rectangle>();
    private boolean pasladernierroute = false;

    public RouteDroite(Dimension dPnlBoard){
        longueur = dPnlBoard.height* 6;
        pnllargeur = dPnlBoard.width;
        this.locationY = (longueur -RouteDroite.getLongueur() / 3) * -1;
        tRoute = TailleRoute.AleatoireRoute();
        largeur = tRouteForLargeur(tRoute);
        x = aleatoireCentre();
        CONTOUR.add(new Rectangle(x, locationY, largeur, longueur));
    }
  
    public RouteDroite(int decalageY, RouteDroite rDini) {
        this.locationY = (longueur + decalageY) * -1;
        tRoute = TailleRoute.AleatoireRoute(rDini);
        largeur = tRouteForLargeur(tRoute);

        x = aleatoireCentre(rDini.getX());

        CONTOUR.add(new Rectangle(x, locationY, largeur, longueur));
    }

    private int tRouteForLargeur(TailleRoute tr) {
        if (tRoute.equals(TailleRoute.PETIT)) {
            return (pnllargeur / 5);
        } else if (tRoute.equals(TailleRoute.MOYENNE)) {
            return (pnllargeur* 2 / 5);
        } else if (tRoute.equals(TailleRoute.LARGE)) {
            return (pnllargeur * 3 / 5);
        } else if (tRoute.equals(TailleRoute.TRESLARGE)) {
            return (pnllargeur* 4 / 5);
        } else {
            return 400;
        }
    }

    private int aleatoireCentre() {

        int h = 0;
        Random r = new Random();
        if (tRoute.equals(TailleRoute.TRESLARGE)) {
            h = 1;
        } else if (tRoute.equals(TailleRoute.LARGE)) {
            h = r.nextInt(3) + 1;
        } else if (tRoute.equals(TailleRoute.MOYENNE)) {
            h = r.nextInt(5) + 1;
        } else if (tRoute.equals(TailleRoute.PETIT)) {
            h = r.nextInt(7) + 1;
        }

        return (pnllargeur / 10 * (h));

    }

    private int aleatoireCentre(int xini) {
        if (tRoute.equals(TailleRoute.PETIT)) {
            do {
                x = aleatoireCentre();
            } while (Math.abs(x - xini) >= pnllargeur/ 10); // Fait en sorte quune RouteDroite petite ne peut etre decale que de 100 pixel
            return x;
        } else if (tRoute.equals(TailleRoute.MOYENNE)){ 
            do {
                x = aleatoireCentre();
            } while (Math.abs(x - xini) > pnllargeur/ 5);
            return x;
            
        } else {
            return aleatoireCentre();
        }
    }

    // Getter et Setter
    @Override
    public ArrayList<Rectangle> getBounds() {
        CONTOUR.get(0).setBounds(x, locationY, largeur, longueur);
        return CONTOUR;
    }

    @Override
    public int getLocationY() {
        return locationY;
    }

    @Override
    public void deplacement() {
        locationY = locationY + Joueur.getVitessejoueur();
    }

    @Override
    public int getMilieuX() {
        return x;

    }

    public int getLargeur() {
        return largeur;
    }

    public static int getLongueur() {
        return longueur;
    }

    public int getX() {
        return x;
    }

    public void setPasladernierroute(boolean pasladernierroute) {
        this.pasladernierroute = pasladernierroute;
    }

    public boolean isPasladernierroute() {
        return pasladernierroute;
    }

    public TailleRoute gettRoute() {
        return tRoute;
    }
}
