/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.graphique;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tp2.execution.ExecutionMenu;

/**
 *
 * @author Vincent
 */
public class FrameMenu extends JFrame {

    private FrameMenu instance = new FrameMenu();
    private Dimension dFrame = new Dimension(1000, 700);
    private JPanel highscore = new JPanel();
    private JPanel background = new JPanel ();
    // private Image background;
    // private Image fleche;
    
    private FrameMenu() {
            
            
            this.add(background);
            
        
        
        
            addKeyListener(new KeyListener() {

                @Override
                public void keyPressed(KeyEvent e) {
                    ExecutionMenu.getInstance().keyPressed(e.getKeyCode());
                }

                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        
        setTitle("SpyHunter");
        setSize(dFrame);
        setPreferredSize(dFrame);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
