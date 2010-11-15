/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.umk.mat.imare.gui;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDesktopPane;
import pl.umk.mat.imare.exception.FileDoesNotExistException;
import pl.umk.mat.imare.io.Wave;

/**
 *
 * @author pieterer
 */
public class WaveOpener implements Runnable {

    final File file;
    final JDesktopPane pane;

    WaveOpener(JDesktopPane pane, String path) {
        this.pane = pane;
        this.file = new File(path);
    }

    @Override
    public void run() {
        final ProgressFrame pf = new ProgressFrame("Ładowanie pliku audio...", null);
        try {
            EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    pane.add(pf);
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(WaveOpener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(WaveOpener.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Wave w = Wave.create(file, pf);



        } catch (Exception e) {
            pf.jobFailed(null, e.getMessage());
        }
    }
}
