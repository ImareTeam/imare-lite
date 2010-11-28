/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NoteFrame.java
 *
 * Created on 2010-03-27, 08:39:32
 */
package pl.umk.mat.imare.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JViewport;
import pl.umk.mat.imare.midi.MidiPlayer;
import pl.umk.mat.imare.midi.MidiPlayerListener;
import pl.umk.mat.imare.reco.StaveData;

/**
 *
 * @author morti
 */
public class NoteFrame extends javax.swing.JInternalFrame {

  public NoteFrame() {
    initComponents();
  }
//    private void initMidi() {
//        /*try {
//        midi = new MidiPlayer();
//        midi.addListener(this);
//        //			if (stavePanel.getStaveData() != null) {
//        //				midi.createFromNotes(stavePanel.getStaveData());
//        //			}
//        midi.addMidiPlayerListener(this);
//
//        String[] s=midi.listAllInstuments();
//        for(int i=0;i<s.length;i++)
//        {
//        jComboBox1.addItem(s[i]);
//        }
//        if(s.length>0)
//        {
//        previnstrument=s[0];
//        }
//        else
//        {
//        playButton.setEnabled(false);
//        jComboBox1.setEnabled(false);
//        }
//        } catch (MidiUnavailableException ex) {
//        playButton.setEnabled(false);
//        jComboBox1.setEnabled(false);
//        Logger.getLogger(NoteFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }*/
//        midi = new MidiPlayer();
//        midi.addListener(this);
////	if (stavePanel.getStaveData() != null) {
////		midi.createFromNotes(stavePanel.getStaveData());
////	}
//        midi.addMidiPlayerListener(this);
//
//        String[] s = midi.listAllInstuments();
//		Arrays.sort(s);
//		int index = Arrays.binarySearch(s, "Piano");
//
//        for (int i = 0; i < s.length; i++) {
//            instrumentComboBox.addItem(s[i]);
//        }
//        if (s.length > 0) {
//            previnstrument = s[0];
//        } else {
//            playButton.setEnabled(false);
//            instrumentComboBox.setEnabled(false);
//        }
//        if (!midi.getInitialized()) {
//            playButton.setEnabled(false);
//            instrumentComboBox.setEnabled(false);
//        }
//
//	if (index>=0) instrumentComboBox.setSelectedIndex(index);
//    }
//
//    /** This method is called from within the constructor to
//     * initialize the form.
//     * WARNING: Do NOT modify this code. The content of this method is
//     * always regenerated by the Form Editor.
//     */
//    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    panel = new pl.umk.mat.imare.gui.NotePanel();

    setClosable(true);
    setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
    setMaximizable(true);
    setResizable(true);
    setTitle("Nuty");
    addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
      public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
      }
      public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
      }
      public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
        onClose(evt);
      }
      public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
      }
      public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
      }
      public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
      }
      public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
      }
    });
    addAncestorListener(new javax.swing.event.AncestorListener() {
      public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
      }
      public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
      }
      public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
        onAncestorRemoved(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
    );

    setBounds(0, 0, 613, 312);
  }// </editor-fold>//GEN-END:initComponents

        private void onClose(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_onClose
//            if (midi != null) {
//                stopButtonActionPerformed(null);
////            midi.stop();
////            midi.close();
////            midi=null;
//            }
        }//GEN-LAST:event_onClose

        private void onAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_onAncestorRemoved
//            if (midi != null) {
//                stopButtonActionPerformed(null);
////            midi.stop();
////            midi.close();
////            midi=null;
//            }
        }//GEN-LAST:event_onAncestorRemoved

        private void instrumentComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instrumentComboBoxActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_instrumentComboBoxActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private pl.umk.mat.imare.gui.NotePanel panel;
  // End of variables declaration//GEN-END:variables

//    @Override
//    public void meta(MetaMessage meta) {
//        if (meta.getType() == MidiPlayer.END_OF_TRACK_MESSAGE) {
//            stopButtonActionPerformed(null);
//        }
//    }
//
//    @Override
//    public void positionChanged(long newPosition) {
//        double sec = (double) newPosition / 1000000.0;
//        double tempo = midi.getTempo();
//        int x = (int) (sec * tempo
//                * stavePanel.getPixelsPerWholeNote()) + stavePanel.getStartPosition();
//
//        cursorPanel.setLocation(x, 0);
//        if (x >= (scrollPane.getHorizontalScrollBar().getValue() + scrollPane.getHorizontalScrollBar().getVisibleAmount()) / (double) scrollPane.getHorizontalScrollBar().getMaximum() * stavePanel.getWidth()) {
//            scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getValue() + scrollPane.getHorizontalScrollBar().getVisibleAmount());
//        }
//    }
//
//    @Override
//    public void notifyFinished() {
//        if (cursorPanel != null) {
//            cursorPanel.setLocation(-1, 0);
//        }
//    }

  public void setNotes(StaveData notes) {
    panel.setNotes(notes);
  }
//        stavePanel.setStaveData(notes);
//        try {
//            StaveData sd = stavePanel.getStaveData();
//            if (sd != null && midi != null && notes != null) {
//                midi.setTempo(notes.getTempo());
//                midi.createFromNotes(sd);
//            }
//
//            setTime();
//
//        } catch (InvalidMidiDataException ex) {
//            Logger.getLogger(NoteFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        layerPane.setBackground(Color.white);
//        scrollPane.setBackground(Color.white);
//        stavePanel.setBackground(Color.white);
//
//        layerPane.setSize(stavePanel.getSize());
//        layerPane.setPreferredSize(stavePanel.getSize());
//
//        cursorPanel.setSize(1, stavePanel.getHeight());
//
//        stavePanel.setSize(
//                Math.max(stavePanel.getWidth(), scrollPane.getWidth()),
//                Math.max(stavePanel.getHeight(), scrollPane.getHeight()));
////        timePanel.setSize(stavePanel.getWidth(), timePanel.getHeight());
//
//        if (cursorPanel != null) {
//            cursorPanel.setSize(1, scrollPane.getHeight());
//        }
//    }

//    @Override
//    public void dispose() {
//        super.dispose();
//        if (midi != null) {
//            midi.stop();
//            midi.close();
//            midi = null;
//        }
//    }
//
//    private void setTime() {
////        DecimalFormat df = new DecimalFormat("00.000");
////        //double length = midi.getLength() / 1000000.0;
////        double length = (stavePanel.getWidth() - stavePanel.getStartPosition()) / (double) stavePanel.getPixelsPerWholeNote() / (double) midi.getTempo();
////        String str = "<html><b>Długość:</b> ";
////
////        if (length >= 60) {
////
////            str += Integer.toString((int) (length / 60)) + ":";
////            length %= 60;
////        }
////        str += df.format(length) + "</html>";
////        lengthLabel.setText(str);
//    }
}
