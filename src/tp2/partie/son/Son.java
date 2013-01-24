package tp2.partie.son;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Vincent
 */
public class Son implements Runnable {

    private boolean mute = false;
    private boolean bit8;
    private boolean death;
    private Chanson toune = Chanson.getRandomChanson();
    private FloatControl volumepg = null, volumepg8 = null, volumejb = null, volumejb8 = null;
    private Clip peterGunn, peterGunn8b, jBound, jBound8b;
    public Clip deathC;
    private boolean forcebreak = false;

    public Son() throws LineUnavailableException {

        jBound = loadClip(new File("jamesbond.wav"));
        jBound8b = loadClip(new File("jamesbond8bit.wav"));
        peterGunn = loadClip(new File("petergunn.wav"));
        peterGunn8b = loadClip(new File("petergunn8bit.wav"));
        deathC = loadClip(new File("mort.wav"));
        bit8 = true;
        death = false;
        new Thread(this).start();

    }

    @Override
    public void run() {

        while (true) {
            if (bit8) {
                if (toune.equals(Chanson.JBOND)) {
                    playClip(jBound8b, bit8);
                }
                if (toune.equals(Chanson.PETERGUNN)) {
                    playClip(peterGunn8b, bit8);
                }

            } else if (!bit8) {
                if (toune.equals(Chanson.JBOND)) {
                    playClip(jBound, bit8);
                }
                if (toune.equals(Chanson.PETERGUNN)) {
                    playClip(peterGunn, bit8);
                }
            }
            if (death) {
                System.out.println("death");
                deathC.start();
                try {
                    Thread.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Son.class.getName()).log(Level.SEVERE, null, ex);
                }
                while (deathC.isRunning()) {
                    System.out.print("rwe");
                }
                deathC.setFramePosition(0);
                death = false;
            }

        }

    }

    private void playClip(Clip rClip, boolean bit8d) {
        rClip.start();
        try {
            Thread.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(Son.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (rClip.isRunning()) {
            if (death || bit8 != bit8d || forcebreak) {
                rClip.stop();
                break;

            }
        }
        if (bit8 == bit8d && !forcebreak) {
            toune = Chanson.prochaineChanson(toune);
        } else if (forcebreak) {
            forcebreak = false;
        }
        rClip.setFramePosition(0);
    }

    public void muteAll() {
        if (!mute) {
            ((FloatControl) jBound.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-80.0f);
            ((FloatControl) jBound8b.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-80.0f);
            ((FloatControl) peterGunn.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-80.0f);
            ((FloatControl) peterGunn8b.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-80.0f);
            ((FloatControl) deathC.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-80.0f);
            mute = true;
        } else {
            ((FloatControl) jBound.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            ((FloatControl) jBound8b.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            ((FloatControl) peterGunn.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            ((FloatControl) peterGunn8b.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            ((FloatControl) deathC.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            mute = false;
        }

    }

    private Clip loadClip(File pFile) throws LineUnavailableException {
        Clip ruiClip = null;
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(pFile);
            ruiClip = AudioSystem.getClip();
            ruiClip.open(audioIn);
        } catch (UnsupportedAudioFileException ex) {
        } catch (IOException ex) {

            System.out.println("Il manque un fichier musique.");
        }
        return ruiClip;
    }

    public void changerToune() {
        toune = Chanson.prochaineChanson(toune);
        forcebreak = true;
    }

    // Getter et Setter
    public void setBit8(boolean bit8) {
        this.bit8 = bit8;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }

    public boolean getBit8() {
        return bit8;
    }

    public boolean getDeath() {
        return death;
    }
}
