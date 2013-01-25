/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.graphique;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tp2.partie.Partie;
import tp2.partie.objets.voiture.Joueur;

/**
 * Gestion des meilleurs scores.
 *
 * @author Vincent
 */
public class Stats extends JPanel {

    public Stats() {
        setBackground(Color.black);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintStats(g);
    }

    private void paintStats(Graphics g) {

        g.setColor(Color.WHITE);
        g.drawString(" patate", 15, 15);

        Joueur heros = Partie.getInstance().getHeros();

        int x = 15;
        for (int i = heros.getNbVie(); i == 0; i--) {
            g.drawImage(heros.getImg(), x, 700, this);
            x = x + 30;
        }

    }
}
