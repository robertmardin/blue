/*
 * blue - object composition environment for csound
 * Copyright (C) 2012
 * Steven Yi <stevenyi@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package blue.score.layers.patterns.ui;

import blue.score.layers.Layer;
import blue.score.layers.patterns.core.PatternLayer;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

/**
 *
 * @author stevenyi
 */
public class PatternLayerPanel extends javax.swing.JPanel {
    private final PatternLayer patternLayer;

    /**
     * Creates new form PatternLayerPanel
     */
    public PatternLayerPanel(PatternLayer patternLayer) {
        this.patternLayer = patternLayer;
        initComponents();
        Dimension d = new Dimension(100, Layer.LAYER_HEIGHT);
        this.setSize(d);
        this.setPreferredSize(d);
        
        this.nameLabel.setText(patternLayer.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        muteToggleButton = new javax.swing.JToggleButton();
        soloToggleButton = new javax.swing.JToggleButton();
        setSoundObjectButton = new javax.swing.JButton();
        editSoundObjectButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 3, 0, 3));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(17, 17));
        jPanel1.setLayout(new java.awt.CardLayout());

        nameLabel.setText(org.openide.util.NbBundle.getMessage(PatternLayerPanel.class, "PatternLayerPanel.nameLabel.text")); // NOI18N
        nameLabel.setMinimumSize(new java.awt.Dimension(0, 15));
        nameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nameLabelMousePressed(evt);
            }
        });
        jPanel1.add(nameLabel, "label");

        nameText.setText(org.openide.util.NbBundle.getMessage(PatternLayerPanel.class, "PatternLayerPanel.nameText.text")); // NOI18N
        nameText.setMinimumSize(new java.awt.Dimension(0, 15));
        nameText.setPreferredSize(new java.awt.Dimension(115, 17));
        nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextActionPerformed(evt);
            }
        });
        nameText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameTextFocusLost(evt);
            }
        });
        nameText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameTextKeyPressed(evt);
            }
        });
        jPanel1.add(nameText, "textField");

        add(jPanel1);

        muteToggleButton.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        muteToggleButton.setText(org.openide.util.NbBundle.getMessage(PatternLayerPanel.class, "PatternLayerPanel.muteToggleButton.text")); // NOI18N
        muteToggleButton.setFocusPainted(false);
        muteToggleButton.setFocusable(false);
        muteToggleButton.setMargin(new java.awt.Insets(0, 3, 0, 3));
        muteToggleButton.setMaximumSize(new java.awt.Dimension(19, 19));
        muteToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteToggleButtonActionPerformed(evt);
            }
        });
        add(muteToggleButton);

        soloToggleButton.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        soloToggleButton.setText(org.openide.util.NbBundle.getMessage(PatternLayerPanel.class, "PatternLayerPanel.soloToggleButton.text")); // NOI18N
        soloToggleButton.setFocusPainted(false);
        soloToggleButton.setFocusable(false);
        soloToggleButton.setMargin(new java.awt.Insets(0, 3, 0, 3));
        soloToggleButton.setMaximumSize(new java.awt.Dimension(19, 19));
        soloToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soloToggleButtonActionPerformed(evt);
            }
        });
        add(soloToggleButton);

        setSoundObjectButton.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        setSoundObjectButton.setText(org.openide.util.NbBundle.getMessage(PatternLayerPanel.class, "PatternLayerPanel.setSoundObjectButton.text")); // NOI18N
        setSoundObjectButton.setFocusPainted(false);
        setSoundObjectButton.setFocusable(false);
        setSoundObjectButton.setMargin(new java.awt.Insets(0, 3, 0, 3));
        setSoundObjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setSoundObjectButtonActionPerformed(evt);
            }
        });
        add(setSoundObjectButton);

        editSoundObjectButton.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        editSoundObjectButton.setText(org.openide.util.NbBundle.getMessage(PatternLayerPanel.class, "PatternLayerPanel.editSoundObjectButton.text")); // NOI18N
        editSoundObjectButton.setToolTipText(org.openide.util.NbBundle.getMessage(PatternLayerPanel.class, "PatternLayerPanel.editSoundObjectButton.toolTipText")); // NOI18N
        editSoundObjectButton.setFocusPainted(false);
        editSoundObjectButton.setFocusable(false);
        editSoundObjectButton.setMargin(new java.awt.Insets(0, 3, 0, 3));
        editSoundObjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSoundObjectButtonActionPerformed(evt);
            }
        });
        add(editSoundObjectButton);
    }// </editor-fold>//GEN-END:initComponents

    private void nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextActionPerformed
         if (patternLayer == null) {
            return;
        }

        patternLayer.setName(nameText.getText());
        nameLabel.setText(patternLayer.getName());

        ((CardLayout) jPanel1.getLayout()).show(jPanel1, "label");
    }//GEN-LAST:event_nameTextActionPerformed

    private void nameTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameTextFocusLost
        ((CardLayout) jPanel1.getLayout()).show(jPanel1, "label");
    }//GEN-LAST:event_nameTextFocusLost

    private void nameTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameTextKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ((CardLayout) jPanel1.getLayout()).show(jPanel1, "label");
        }
    }//GEN-LAST:event_nameTextKeyPressed

    private void muteToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteToggleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_muteToggleButtonActionPerformed

    private void soloToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soloToggleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soloToggleButtonActionPerformed

    private void setSoundObjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setSoundObjectButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setSoundObjectButtonActionPerformed

    private void editSoundObjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSoundObjectButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editSoundObjectButtonActionPerformed

    private void nameLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameLabelMousePressed
        if(evt.getClickCount() == 2) {
            editName();
        }
    }//GEN-LAST:event_nameLabelMousePressed

    public void editName() {
        if (patternLayer == null) {
            return;
        }

        nameText.setText(patternLayer.getName());
        ((CardLayout) jPanel1.getLayout()).show(jPanel1, "textField");
        nameText.requestFocusInWindow();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editSoundObjectButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton muteToggleButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameText;
    private javax.swing.JButton setSoundObjectButton;
    private javax.swing.JToggleButton soloToggleButton;
    // End of variables declaration//GEN-END:variables
}