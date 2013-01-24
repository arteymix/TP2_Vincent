/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.autres;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 *
 * @author Vincent
 */
public class Explosion extends ObjGenerique {

    private int iexplo = -1;
    private ArrayList<Image> exploImages = new ArrayList<Image>();

    public Explosion(int x, int y) {
        super(50, 50, x, y, 0, 0);
        exploImages.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/explo01.png")));
        exploImages.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/explo02.png")));
        exploImages.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/explo03.png")));
        exploImages.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/explo04.png")));
        exploImages.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/explo05.png")));
        exploImages.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/explo06.png")));
        exploImages.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/explo07.png")));
        exploImages.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/explo08.png")));
        exploImages.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/explo09.png")));
        exploImages.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/explo10.png")));
        exploImages.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/explo11.png")));

    }

    public Image getImages() {
        iexplo++;
        if (iexplo/2 < exploImages.size()) {
            return exploImages.get(iexplo/2);
        } else {
            iexplo = -1;
            return null;
        }

    }
}
