/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.execution;

import java.awt.event.KeyEvent;

/**
 *
 * @author Vincent
 */
public class ExecutionMenu {

    private static ExecutionMenu instance = new ExecutionMenu();

    private ExecutionMenu() {
    }

    public void keyPressed(int key) {

        switch (key) {
            case KeyEvent.VK_ESCAPE://escape
                break;
            case KeyEvent.VK_SPACE: // space
                break;
            case KeyEvent.VK_ENTER:
            
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

    public static ExecutionMenu getInstance() {
        return instance;
    }
    

}
