/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProgressFrame.java
 *
 * Created on 2010-04-23, 11:44:49
 */

package pl.umk.mat.imare.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.beans.PropertyVetoException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;

/**
 *
 * @author morti, pieterer
 */
abstract public class ProgressThreadFrame extends javax.swing.JInternalFrame {

    private volatile boolean terminated = false;
    private double startTime = 0.0;

    private double sumX = 0.0;
    private double sumY = 0.0;
    private double sumXY = 0.0;
    private double sumX2 = 0.0;
    private int count = 0;

    /** Creates new form ProgressFrame */
    public ProgressThreadFrame(JDesktopPane parent) {
        initComponents();
        parent.add(this);
        int x = (parent.getWidth() - getWidth()) / 2;
        int y = (parent.getHeight() - getHeight()) / 2;
        setLocation(x, y);

        setVisible(true);
        try {
            setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ProgressFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    progressBar = new javax.swing.JProgressBar();
    label = new javax.swing.JLabel();

    jLabel1.setText("jLabel1");

    setTitle("Działanie w toku...");
    setDoubleBuffered(true);

    label.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    label.setText("...");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
          .addComponent(progressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(label)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel label;
  private javax.swing.JProgressBar progressBar;
  // End of variables declaration//GEN-END:variables

    private double getTime() {
        return (double)System.currentTimeMillis()/1000.0 - startTime;
    }

    private void setFinished() {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    label.setText("zakończono");
                    progressBar.setValue(progressBar.getMaximum());
                    after();
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(ProgressFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ProgressFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                dispose();
            }
        });
    }

    private void setFailed(final String message) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                label.setForeground(Color.RED);
                label.setText("błąd: "+message);
                setClosable(true);
            }
        });
    }

    public boolean hasBeenTerminated() {
        return terminated;
    }

    public void setProgress(final float progress) {
      final double t = getTime();
      sumX += t;
      sumY += progress;
      sumXY += t*progress;
      sumX2 += t*t;
      ++count;
      
      double t0 = Double.NaN;
      double coeff = 1.0 / (sumX2 - sumX*sumX);
      if (!Double.isInfinite(coeff)) {
        double a = (sumXY - sumX*sumY) * coeff;
        double b = (sumY - a*sumX)/count;
        t0 = (1-b)/a;
      }

      final double dt = t0 - t;
      EventQueue.invokeLater(new Runnable() {
          @Override
          public void run() {
              String fmt;
              if (Double.isInfinite(dt)) {
                  fmt = "∞";
              } else if (Double.isNaN(dt)) {
                  fmt = "?";
              } else {
                  fmt = Long.toString(Math.round(dt));
              }
              label.setText("pozostało: "+fmt+" s");
              progressBar.setValue((int)Math.floor(progress * progressBar.getMaximum()));
          }
      });
    }

    public void startThread() {
       new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    startTime = getTime();
                    work();
                    setFinished();
                } catch (Exception e) {
                    setFailed(e.getMessage());
                }
            }
       }).start();
    }

    public void stopThread() {
        terminated = true;
    }

    abstract protected void work() throws Exception;
    abstract protected void after();
}
