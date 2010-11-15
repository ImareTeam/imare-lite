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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;

/**
 *
 * @author morti, pieterer
 */
abstract public class ProgressThreadFrame extends javax.swing.JInternalFrame {

    private volatile boolean terminated = false;
    private double startTime = Double.NaN;
    private double lastTime = Double.NaN;
    private float lastProgress = Float.NaN;

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
                    .addComponent(label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(progressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel label;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables

    private static double getTime() {
        return (double)System.currentTimeMillis() / 1000.0;
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
        final float level = lastProgress;
        final double t0 = lastTime;
        final double t = getTime();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                String fmt;
                double est = (1.0f-progress) * (t-t0)/(progress-level);
                if (Double.isInfinite(est)) {
                    fmt = "∞";
                } else if (Double.isNaN(est)) {
                    fmt = "?";
                } else {
                    fmt = Long.toString(Math.round(est));
                }
                label.setText("pozostało: "+fmt+" s");
                progressBar.setValue((int)Math.floor(progress * progressBar.getMaximum()));
            }
        });
        lastProgress = progress;
        lastTime = t;
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
