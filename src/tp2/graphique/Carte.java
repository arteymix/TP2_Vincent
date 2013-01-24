/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.graphique;

import tp2.partie.objets.route.RouteTransition;
import tp2.partie.objets.route.RouteDroite;
import tp2.partie.objets.autres.Arbre;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;
import tp2.partie.Partie;
import tp2.partie.objets.autres.Background;
import tp2.partie.objets.autres.Explosion;
import tp2.partie.objets.route.Route;
import tp2.partie.objets.armes.Tir;
import tp2.partie.objets.voiture.Civil;
import tp2.partie.objets.voiture.Voiture;
import tp2.partie.objets.voiture.Joueur;

/**
 *
 * @author user1
 */
public class Carte extends JPanel {

    public Carte(Dimension dPnlBoard) {
        setBackground(Color.green);
        this.setSize(dPnlBoard);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {

        //super.paint(g);
        paintBackground(g);

        paintRoutes(g);

        paintVoitures(g);

        paintArbres(g);

        paintTirs(g);

        paintJoueur(g);

        paintExplosions(g);

    }

    private void paintRoutes(Graphics g) {

        for (Route route : (ArrayList<Route>) Partie.getInstance().getRoutes().clone()) {
            g.setColor(Color.GRAY);
            if (route instanceof RouteTransition) {
                for (Rectangle rect : route.getBounds()) {
                    g.fillRect(rect.x, rect.y, rect.width, rect.height);
                }
            } else {
                g.fillRect(((RouteDroite) route).getX(), route.getLocationY(), ((RouteDroite) route).getLargeur(), ((RouteDroite) route).getLongueur());
            }
        }

    }

    private void paintTirs(Graphics g) {
        for (Tir pewpew : (ArrayList<Tir>) Partie.getInstance().getTirs().clone()) {

            g.drawImage(pewpew.getImage(), pewpew.getX(), pewpew.getY(), pewpew.getLARGEUR(), pewpew.getLONGUEUR(), null);
        }
    }

    private void paintVoitures(Graphics g) {
        for (Voiture toto : (ArrayList<Voiture>) Partie.getInstance().getVoitures().clone()) {
            //  g.setColor(Color.PINK);
            // g.fillRect(toto.getX(), toto.getY(), toto.getLARGEUR(), toto.getLONGUEUR());
            g.drawImage(((Civil) toto).getImg(), toto.getX(), toto.getY(), null);
        }
    }

    private void paintArbres(Graphics g) {

        for (Arbre branche : (ArrayList<Arbre>) Partie.getInstance().getArbres().clone()) {
            g.drawImage(branche.getImg(), branche.getX(), branche.getY(), branche.getLARGEUR(), branche.getLONGUEUR(), null);
        }

    }

    private void paintJoueur(Graphics g) {
        Joueur heros = Partie.getInstance().getHeros();
        g.drawImage(heros.getImg(), heros.getX(), heros.getY(), heros.getLARGEUR(), heros.getLONGUEUR(), null);
    }

    private void paintExplosions(Graphics g) {
        for (Explosion explo : (ArrayList<Explosion>) Partie.getInstance().getExplosions().clone()) {
            g.drawImage(explo.getImages(), explo.getX(), explo.getY(), explo.getLARGEUR(), explo.getLONGUEUR(), null);
        }
    }

    private void paintBackground(Graphics g) {
        for (Background b : (ArrayList<Background>) Partie.getInstance().getPnlBackground().clone()) {
            g.drawImage(b.getImg(), 0, b.getY(), b.getLARGEUR(), b.getLONGUEUR(), null);
        }
    }
}
