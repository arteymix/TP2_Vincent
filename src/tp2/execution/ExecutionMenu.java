/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.execution;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tp2.graphique.FrameMenu;
import tp2.partie.Partie;

/**
 *
 * @author Vincent
 */
public class ExecutionMenu implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE://escape
                break;
            case KeyEvent.VK_SPACE: // space
                break;
            case KeyEvent.VK_ENTER:

                switch (FrameMenu.getInstance().getChoixMenu()) {

                    case COMMENCER:
                    default:
                        FrameMenu.getInstance().setVisible(false);
                        Partie.newInstance();
                        break;

                }


                break;
            case KeyEvent.VK_UP:



                break;
            case KeyEvent.VK_RIGHT:
                break;
            case KeyEvent.VK_DOWN:




                break;
            case KeyEvent.VK_M:

                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
