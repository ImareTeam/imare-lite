/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RealtimeFrame.java
 *
 * Created on 2010-04-21, 19:13:35
 */
package pl.umk.mat.imare.gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import pl.umk.mat.imare.reco.Note;
import pl.umk.mat.imare.reco.RTListener;
import pl.umk.mat.imare.reco.RTRecognizer;
import pl.umk.mat.imare.reco.RTRecognizer.PixelType;

/**
 *
 * @author morti
 */
public class RealtimeFrame extends javax.swing.JInternalFrame implements RTListener {

//  private StaveData data;
  private RTRecognizer capture;
  private Timer timer = new Timer();
  private TimerTask timeUpdateTask;
  private SpectralPanel spectralPanel;

  /** Creates new form RealtimeFrame */
  public RealtimeFrame() {

    initComponents();

    spectralPanel = new SpectralPanel();
    spectrumContainingPanel.add(spectralPanel, "specPanel");

//    data = new StaveData();
    stopRecordingButton.setEnabled(false);

    notePanel.getStavePanel().addComponentListener(new ComponentListener() {

      @Override
      public void componentResized(ComponentEvent e) {
        notePanel.scrollToEnd();
      }

      @Override
      public void componentMoved(ComponentEvent e) {
      }

      @Override
      public void componentShown(ComponentEvent e) {
      }

      @Override
      public void componentHidden(ComponentEvent e) {
      }
    });
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        toolBar = new javax.swing.JToolBar();
        recordButton = new javax.swing.JButton();
        stopRecordingButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        label = new javax.swing.JLabel();
        notePanel = new pl.umk.mat.imare.gui.NotePanel();
        spectrumContainingPanel = new javax.swing.JPanel();

        setClosable(true);
        setForeground(java.awt.Color.white);
        setMaximizable(true);
        setResizable(true);
        setTitle("Przetwarzanie w czasie rzeczywistym");
        setDoubleBuffered(true);
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        recordButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/umk/mat/imare/gui/gfx/record.png"))); // NOI18N
        recordButton.setFocusable(false);
        recordButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        recordButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        recordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordButtonActionPerformed(evt);
            }
        });
        toolBar.add(recordButton);

        stopRecordingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/umk/mat/imare/gui/gfx/stop.png"))); // NOI18N
        stopRecordingButton.setFocusable(false);
        stopRecordingButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stopRecordingButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        stopRecordingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopRecordingButtonActionPerformed(evt);
            }
        });
        toolBar.add(stopRecordingButton);
        toolBar.add(jSeparator1);
        toolBar.add(label);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(toolBar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(notePanel, gridBagConstraints);

        spectrumContainingPanel.setMinimumSize(new java.awt.Dimension(100, 0));
        spectrumContainingPanel.setPreferredSize(new java.awt.Dimension(100, 340));
        spectrumContainingPanel.setLayout(new java.awt.CardLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(spectrumContainingPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void recordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordButtonActionPerformed
    if (capture != null) {
      return;
    }

    try {
      capture = new RTRecognizer();
      capture.addListener(this);
      capture.addListener(spectralPanel);
      capture.start();
    } catch (LineUnavailableException ex) {
      Logger.getLogger(RealtimeFrame.class.getName()).log(Level.SEVERE, null, ex);
      capture = null;
    }

//		data = new StaveData();

    timeUpdateTask = new TimerTask() {

      int secs = 0;

      @Override
      public void run() {
        secs++;

        label.setText("Nagrywanie... (" + secs + "s)");
      }

      @Override
      public boolean cancel() {
        label.setText("");
        return super.cancel();
      }
    };

    timer.scheduleAtFixedRate(timeUpdateTask, 1000, 1000);
    recordButton.setEnabled(false);
    stopRecordingButton.setEnabled(true);
    notePanel.setNotes(null);
    notePanel.disableMidiButtons();
	}//GEN-LAST:event_recordButtonActionPerformed

	private void stopRecordingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopRecordingButtonActionPerformed
    if (capture == null) {
      return;
    }

    if (timeUpdateTask != null) {
      timeUpdateTask.cancel();
    }
    timer.purge();

    capture.terminate();
    capture = null;
    stopRecordingButton.setEnabled(false);
    recordButton.setEnabled(true);
    notePanel.enableMidiButtons();
	}//GEN-LAST:event_stopRecordingButtonActionPerformed

	private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
	}//GEN-LAST:event_formAncestorAdded
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JLabel label;
    private pl.umk.mat.imare.gui.NotePanel notePanel;
    private javax.swing.JButton recordButton;
    private javax.swing.JPanel spectrumContainingPanel;
    private javax.swing.JButton stopRecordingButton;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

  /*
  @Override
  public void bufferFilled(AudioCaptureThread act, int[] buffer, int samples) {
  Wave wave = Wave.fromSamples(act.getFormat(), buffer);
  Recognizer reco = new Recognizer(wave);
  reco.addRecognitionListener(this);
  reco.start();
  }

  @Override
  public void recognitionFinished(Recognizer recognizer, boolean cancelled) {
  Transcriber t = new Transcriber(recognizer.getVoice());
  t.addTranscriptionListener(this);
  t.start();
  }

  @Override
  public void progressChanged(Recognizer recognizer, float newProgress) {
  }

  @Override
  public void transcriptionFinished(Transcriber transcriber) {
  StaveData sd = transcriber.getStaveData();
  //		stavePanel1.setStaveData(sd);
  float maxOff = 0;

  for (Note n : sd.top.getNotes()) {
  n.setOffset(n.getOffset() + offset);
  data.top.addNote(n);
  if (n.getOffset() > maxOff) {
  maxOff = n.getOffset();
  }
  }

  for (Note n : sd.bottom.getNotes()) {
  n.setOffset(n.getOffset() + offset);
  data.bottom.addNote(n);
  if (n.getOffset() > maxOff) {
  maxOff = n.getOffset();
  }
  }

  offset = maxOff;

  stavePanel1.setStaveData(data);
  //		jScrollPane1.getHorizontalScrollBar().setValue(jScrollPane1.getHorizontalScrollBar().getMaximum());
  }
   */
  @Override
  public void noteFound(RTRecognizer reco, Note n) {
//        StaffData staff = (n.getPitch() >= Sonst.TOP_STAFF_LOW_PITCH_BOUND) ? data.top : data.bottom;
//        staff.addNote(n);
//		notePanel.setNotes(data);

    notePanel.addNote(n);
  }

  @Override
  public void recognitionFinished(RTRecognizer reco) {
    capture = null;
  }

  @Override
  public void dispose() {
    if (capture != null) {
      capture.terminate();
    }
    if (timeUpdateTask != null) {
      timeUpdateTask.cancel();
    }
    timer.purge();
    timer.cancel();
    notePanel.close();
    super.dispose();
  }

  @Override
  public void spectrumNotification(RTRecognizer reco, final double[] spectrum, final PixelType[] active) {
  }
}
