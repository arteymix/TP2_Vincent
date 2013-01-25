/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.graphique.panels;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 * Panneau affiché quand le jeux est en pause.
 *
 * @author Usager
 */
public class PanelPause extends JPanel {

    /**
     * Image affichée dans le panneau.
     */
    private Image img;

    public PanelPause() {
        img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/Pause.PNG"));
        setVisible(false);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
