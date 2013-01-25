/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.graphique;

import tp2.graphique.panels.PanelPause;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import tp2.execution.ExecutionPartie;
import tp2.execution.ExecutionPause;


/**
 *
 * @author Vincent
 */
public class FramePartie extends JFrame {

    private Carte pnlBoard;
    private PanelPause fpause;
    private Dimension dFrame = new Dimension(1005, 775);
    private Stats stat;

    public FramePartie(Dimension dPnlBoard) {
        fpause = new PanelPause();
        pnlBoard = new Carte(dPnlBoard);
        stat = new Stats();
        this.add(pnlBoard);
        this.add(fpause);
        this.add(stat);
        stat.setLocation(0,dPnlBoard.height);        
        
        setTitle("SpyHunter");
        setSize(dFrame);
        setPreferredSize(dFrame);
        setResizable(false);
        setVisible(true);
        setPause(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Carte getPnlBoard() {
        return pnlBoard;
    }

    public void setPause(boolean isPause) {
        for (KeyListener blabla : this.getKeyListeners()) {
            removeKeyListener(blabla);
        }
        if (isPause) {
            pnlBoard.setVisible(false);
            fpause.setVisible(true);
            fpause.invalidate();
            fpause.repaint();

            addKeyListener(new KeyListener() {

                @Override
                public void keyPressed(KeyEvent e) {
                    ExecutionPause.getInstance().keyPressed(e.getKeyCode());
                }

                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        } else {
            fpause.setVisible(false);
            pnlBoard.setVisible(true);
            addKeyListener(new KeyListener() {

                @Override
                public void keyPressed(KeyEvent e) {
                    ExecutionPartie.getInstance().keyPressed(e.getKeyCode());
                }

                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    ExecutionPartie.getInstance().keyReleased(e.getKeyCode());
                }
            });
        }
        invalidate();
        repaint();
    }
}
