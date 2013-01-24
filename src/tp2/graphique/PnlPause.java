/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.graphique;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author Usager
 */
public class PnlPause extends JPanel
{
    
    private Image img;
    
    public PnlPause()
    {
        img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/Pause.PNG"));
        setVisible(false);
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.drawImage(img, 0, 0, null);
    }
}
