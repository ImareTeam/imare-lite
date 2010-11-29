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

import pl.umk.mat.imare.reco.StaveData;

/**
 *
 * @author morti
 */
public class NoteFrame extends javax.swing.JInternalFrame {

  public NoteFrame() {
    initComponents();
  }

  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new pl.umk.mat.imare.gui.NotePanel();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Nuty");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 605, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
        );

        setBounds(0, 0, 613, 312);
    }// </editor-fold>//GEN-END:initComponents

        private void instrumentComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instrumentComboBoxActionPerformed
        }//GEN-LAST:event_instrumentComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pl.umk.mat.imare.gui.NotePanel panel;
    // End of variables declaration//GEN-END:variables


  public void setNotes(StaveData notes) {
    panel.setNotes(notes);
  }

  @Override
  public void dispose(){
      panel.stopMidi();
      super.dispose();
      
  }

}
