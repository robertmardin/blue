/*
 * blue - object composition environment for csound
 * Copyright (C) 2013
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
package blue.score.layers.audio.ui;

import blue.automation.AutomationManager;
import blue.automation.Parameter;
import blue.automation.ParameterIdList;
import blue.mixer.Channel;
import blue.score.layers.audio.core.AudioLayer;
import blue.ui.components.IconFactory;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author stevenyi
 */
public class AudioHeaderLayerPanel extends javax.swing.JPanel
        implements ListSelectionListener, PropertyChangeListener {

    private static AudioLayerPanelMenu OTHER_MENU = null;
    private final AudioLayer audioLayer;
    private static final Border border = BorderFactory.createBevelBorder(
            BevelBorder.RAISED);
    private static final Border selectionBorder = BorderFactory.createBevelBorder(
            BevelBorder.RAISED, Color.GREEN, Color.GREEN.darker());
    private final Channel channel;

    private final ParameterIdList paramIdList;

    boolean updating = false;

    /**
     * Creates new form AudioHeaderLayerPanel
     */
    public AudioHeaderLayerPanel(AudioLayer layer, Channel channel) {
        initComponents();

        setBorder(border);

        this.audioLayer = layer;
        this.channel = channel;

        nameLabel.setText(audioLayer.getName());
        muteToggleButton.setSelected(audioLayer.isMuted());
        soloToggleButton.setSelected(audioLayer.isSolo());

        muteToggleButton.putClientProperty(
                "BlueToggleButton.selectColorOverride", Color.ORANGE.darker());
        soloToggleButton.putClientProperty(
                "BlueToggleButton.selectColorOverride", Color.GREEN.darker());

        paramIdList = audioLayer.getAutomationParameters();

        updateParameterPanel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        muteToggleButton = new javax.swing.JToggleButton();
        soloToggleButton = new javax.swing.JToggleButton();
        automationButton = new javax.swing.JButton();
        otherMenuButton = new javax.swing.JButton();
        paramSelectPanel = new javax.swing.JPanel();
        paramColorSelect = new blue.components.ColorSelectionPanel();
        paramNameLabel = new javax.swing.JLabel();
        paramPreviousButton = new javax.swing.JButton();
        paramNextButton = new javax.swing.JButton();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 3, 0, 3));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(17, 17));
        jPanel1.setLayout(new java.awt.CardLayout());

        nameLabel.setText(org.openide.util.NbBundle.getMessage(AudioHeaderLayerPanel.class, "AudioHeaderLayerPanel.nameLabel.text")); // NOI18N
        nameLabel.setMinimumSize(new java.awt.Dimension(0, 15));
        jPanel1.add(nameLabel, "label");

        nameText.setText(org.openide.util.NbBundle.getMessage(AudioHeaderLayerPanel.class, "AudioHeaderLayerPanel.nameText.text")); // NOI18N
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

        jPanel2.add(jPanel1);

        muteToggleButton.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        muteToggleButton.setText(org.openide.util.NbBundle.getMessage(AudioHeaderLayerPanel.class, "AudioHeaderLayerPanel.muteToggleButton.text")); // NOI18N
        muteToggleButton.setFocusPainted(false);
        muteToggleButton.setFocusable(false);
        muteToggleButton.setMargin(new java.awt.Insets(0, 3, 0, 3));
        muteToggleButton.setMaximumSize(new java.awt.Dimension(19, 19));
        muteToggleButton.setPreferredSize(new java.awt.Dimension(19, 18));
        muteToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteToggleButtonActionPerformed(evt);
            }
        });
        jPanel2.add(muteToggleButton);

        soloToggleButton.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        soloToggleButton.setText(org.openide.util.NbBundle.getMessage(AudioHeaderLayerPanel.class, "AudioHeaderLayerPanel.soloToggleButton.text")); // NOI18N
        soloToggleButton.setFocusPainted(false);
        soloToggleButton.setFocusable(false);
        soloToggleButton.setMargin(new java.awt.Insets(0, 3, 0, 3));
        soloToggleButton.setMaximumSize(new java.awt.Dimension(19, 19));
        soloToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soloToggleButtonActionPerformed(evt);
            }
        });
        jPanel2.add(soloToggleButton);

        automationButton.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        automationButton.setText(org.openide.util.NbBundle.getMessage(AudioHeaderLayerPanel.class, "AudioHeaderLayerPanel.automationButton.text")); // NOI18N
        automationButton.setToolTipText(org.openide.util.NbBundle.getMessage(AudioHeaderLayerPanel.class, "AudioHeaderLayerPanel.automationButton.toolTipText")); // NOI18N
        automationButton.setFocusPainted(false);
        automationButton.setFocusable(false);
        automationButton.setMargin(new java.awt.Insets(5, 0, 4, 0));
        automationButton.setMaximumSize(new java.awt.Dimension(19, 19));
        automationButton.setPreferredSize(new java.awt.Dimension(16, 17));
        automationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                automationButtonActionPerformed(evt);
            }
        });
        jPanel2.add(automationButton);

        otherMenuButton.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        otherMenuButton.setIcon(IconFactory.getDownArrowIcon());
        otherMenuButton.setToolTipText(org.openide.util.NbBundle.getMessage(AudioHeaderLayerPanel.class, "AudioHeaderLayerPanel.otherMenuButton.toolTipText")); // NOI18N
        otherMenuButton.setFocusPainted(false);
        otherMenuButton.setFocusable(false);
        otherMenuButton.setMargin(new java.awt.Insets(5, 0, 4, 0));
        otherMenuButton.setMaximumSize(new java.awt.Dimension(19, 19));
        otherMenuButton.setPreferredSize(new java.awt.Dimension(16, 17));
        otherMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherMenuButtonActionPerformed(evt);
            }
        });
        jPanel2.add(otherMenuButton);

        paramSelectPanel.setFocusable(false);
        paramSelectPanel.setPreferredSize(new java.awt.Dimension(100, 19));
        paramSelectPanel.setLayout(new javax.swing.BoxLayout(paramSelectPanel, javax.swing.BoxLayout.LINE_AXIS));

        paramColorSelect.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        paramColorSelect.setToolTipText(org.openide.util.NbBundle.getMessage(AudioHeaderLayerPanel.class, "AudioHeaderLayerPanel.paramColorSelect.toolTipText")); // NOI18N
        paramColorSelect.setMaximumSize(new java.awt.Dimension(15, 15));
        paramColorSelect.setPreferredSize(new java.awt.Dimension(15, 15));
        paramColorSelect.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                paramColorSelectPropertyChange(evt);
            }
        });
        paramSelectPanel.add(paramColorSelect);

        paramNameLabel.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        paramNameLabel.setText(org.openide.util.NbBundle.getMessage(AudioHeaderLayerPanel.class, "AudioHeaderLayerPanel.paramNameLabel.text")); // NOI18N
        paramNameLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 3, 0, 0));
        paramNameLabel.setFocusable(false);
        paramNameLabel.setMaximumSize(new java.awt.Dimension(32768, 15));
        paramNameLabel.setPreferredSize(new java.awt.Dimension(100, 15));
        paramSelectPanel.add(paramNameLabel);

        paramPreviousButton.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        paramPreviousButton.setIcon(IconFactory.getLeftArrowIcon());
        paramPreviousButton.setToolTipText(org.openide.util.NbBundle.getMessage(AudioHeaderLayerPanel.class, "AudioHeaderLayerPanel.paramPreviousButton.toolTipText")); // NOI18N
        paramPreviousButton.setFocusPainted(false);
        paramPreviousButton.setFocusable(false);
        paramPreviousButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        paramPreviousButton.setMaximumSize(new java.awt.Dimension(15, 15));
        paramPreviousButton.setPreferredSize(new java.awt.Dimension(18, 17));
        paramPreviousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paramPreviousButtonActionPerformed(evt);
            }
        });
        paramSelectPanel.add(paramPreviousButton);

        paramNextButton.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        paramNextButton.setIcon(IconFactory.getRightArrowIcon());
        paramNextButton.setToolTipText(org.openide.util.NbBundle.getMessage(AudioHeaderLayerPanel.class, "AudioHeaderLayerPanel.paramNextButton.toolTipText")); // NOI18N
        paramNextButton.setFocusPainted(false);
        paramNextButton.setFocusable(false);
        paramNextButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        paramNextButton.setMaximumSize(new java.awt.Dimension(15, 15));
        paramNextButton.setPreferredSize(new java.awt.Dimension(17, 17));
        paramNextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paramNextButtonActionPerformed(evt);
            }
        });
        paramSelectPanel.add(paramNextButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paramSelectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(paramSelectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextActionPerformed
        if (audioLayer == null) {
            return;
        }

        audioLayer.setName(nameText.getText());
        nameLabel.setText(audioLayer.getName());

        ((CardLayout) jPanel1.getLayout()).show(jPanel1, "label");
    }//GEN-LAST:event_nameTextActionPerformed

    private void nameTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameTextFocusLost
        if (audioLayer == null) {
            return;
        }

        final var newName = nameText.getText();
        if (!newName.equals(audioLayer.getName())) {
            audioLayer.setName(nameText.getText());
            nameLabel.setText(audioLayer.getName());
        }
        
        ((CardLayout) jPanel1.getLayout()).show(jPanel1, "label");
    }//GEN-LAST:event_nameTextFocusLost

    private void nameTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameTextKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            nameText.setText(audioLayer.getName());
            ((CardLayout) jPanel1.getLayout()).show(jPanel1, "label");
        }
    }//GEN-LAST:event_nameTextKeyPressed

    private void muteToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteToggleButtonActionPerformed
        audioLayer.setMuted(muteToggleButton.isSelected());
    }//GEN-LAST:event_muteToggleButtonActionPerformed

    private void soloToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soloToggleButtonActionPerformed
        audioLayer.setSolo(soloToggleButton.isSelected());
    }//GEN-LAST:event_soloToggleButtonActionPerformed

    private void otherMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherMenuButtonActionPerformed

        if (OTHER_MENU == null) {
            OTHER_MENU = new AudioLayerPanelMenu();
        }
        OTHER_MENU.setAudioLayer(this.audioLayer);
        OTHER_MENU.show(otherMenuButton, 0, otherMenuButton.getHeight());

    }//GEN-LAST:event_otherMenuButtonActionPerformed

    private void automationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_automationButtonActionPerformed
        JPopupMenu menu = new JPopupMenu();
        JMenu channelMenu = AutomationManager.getInstance().buildChannelMenu(
                channel,
                audioLayer.getAutomationParameters()
        );

        menu.add(channelMenu);

        menu.show(automationButton, 0, automationButton.getHeight());
    }//GEN-LAST:event_automationButtonActionPerformed

    private void paramColorSelectPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_paramColorSelectPropertyChange
        if (audioLayer == null || paramIdList == null || updating) {
            return;
        }

        int index = paramIdList.getSelectedIndex();

        if (index < 0) {
            return;
        }

        String id = paramIdList.getParameterId(index);

        Parameter param = AutomationManager.getInstance().getParameter(id);

        if (param != null) {
            param.getLine().setColor(paramColorSelect.getColor());
        }
    }//GEN-LAST:event_paramColorSelectPropertyChange

    private void paramPreviousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paramPreviousButtonActionPerformed
        if (audioLayer == null || paramIdList == null || paramIdList.size() < 2) {
            return;
        }

        int index = paramIdList.getSelectedIndex() - 1;
        if (index < 0) {
            index = paramIdList.size() - 1;
        }
        paramIdList.setSelectedIndex(index);
    }//GEN-LAST:event_paramPreviousButtonActionPerformed

    private void paramNextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paramNextButtonActionPerformed
        if (audioLayer == null || paramIdList == null || paramIdList.size() < 2) {
            return;
        }

        int index = paramIdList.getSelectedIndex() + 1;
        if (index >= paramIdList.size()) {
            index = 0;
        }
        paramIdList.setSelectedIndex(index);
    }//GEN-LAST:event_paramNextButtonActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        if (audioLayer == null) {
            return;
        }

        if (audioLayer.getAutomationParameters().size() > 0) {
            paramSelectPanel.setVisible(getHeight() > 22);
        } else {
            paramSelectPanel.setVisible(false);
        }
    }//GEN-LAST:event_formComponentResized

    public void editName() {
        if (audioLayer == null) {
            return;
        }

        nameText.setText(audioLayer.getName());
        ((CardLayout) jPanel1.getLayout()).show(jPanel1, "textField");
        nameText.requestFocusInWindow();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton automationButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToggleButton muteToggleButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameText;
    private javax.swing.JButton otherMenuButton;
    private blue.components.ColorSelectionPanel paramColorSelect;
    private javax.swing.JLabel paramNameLabel;
    private javax.swing.JButton paramNextButton;
    private javax.swing.JButton paramPreviousButton;
    private javax.swing.JPanel paramSelectPanel;
    private javax.swing.JToggleButton soloToggleButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void removeNotify() {
        if (this.paramIdList != null) {
            paramIdList.removeListSelectionListener(this);
        }
        if (this.audioLayer != null) {
            this.audioLayer.removePropertyChangeListener(this);
        }

        super.removeNotify();

    }

    @Override
    public void addNotify() {
        if (this.paramIdList != null) {
            paramIdList.addListSelectionListener(this);
        }

        if (this.audioLayer != null) {
            this.audioLayer.addPropertyChangeListener(this);
        }

        super.addNotify();

    }

    public void setSelected(boolean val) {
        setBorder(val ? selectionBorder : border);
    }

    private void updateParameterPanel() {
        int index = paramIdList.getSelectedIndex();

        if (paramIdList.size() <= 0 || index < 0) {

            updating = true;
            paramColorSelect.setEnabled(false);
            paramColorSelect.setColor(Color.BLACK);
            updating = false;

            paramNameLabel.setText("No Parameters Available");
            paramNameLabel.setEnabled(false);
            paramNextButton.setEnabled(false);
            paramPreviousButton.setEnabled(false);

            paramSelectPanel.setVisible(false);

            return;
        }

        String id = paramIdList.getParameterId(index);
        Parameter param = AutomationManager.getInstance().getParameter(id);

        if (param == null) {
            updating = true;
            paramColorSelect.setEnabled(false);
            paramColorSelect.setColor(Color.BLACK);
            updating = false;

            paramNameLabel.setText("No Parameters Available");
            paramNameLabel.setEnabled(false);
            paramNextButton.setEnabled(false);
            paramPreviousButton.setEnabled(false);

            paramSelectPanel.setVisible(false);

            return;
        }

        if (getHeight() > 22) {
            paramSelectPanel.setVisible(true);
        }

        updating = true;

        paramColorSelect.setEnabled(true);
        paramColorSelect.setColor(param.getLine().getColor());

        paramNameLabel.setText(param.getName());
        paramNameLabel.setEnabled(true);

        int size = paramIdList.size();

        paramNextButton.setEnabled(true);
        paramPreviousButton.setEnabled(true);

        updating = false;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        updateParameterPanel();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() == this.audioLayer) {
            String propName = evt.getPropertyName();

            switch (propName) {
                case "heightIndex":
                    revalidate();
                    break;
                case "name":
                    nameText.setText(audioLayer.getName());
                    nameLabel.setText(audioLayer.getName());
                    break;
            }
        }
    }

    static class AudioLayerPanelMenu extends JPopupMenu {

        AudioLayer audioLayer = null;
        JMenuItem[] heightItems = new JMenuItem[9];

        public AudioLayerPanelMenu() {
            super();

            JMenu layerHeightMenu = new JMenu("Layer Height");

            ActionListener al = (ActionEvent ae) -> {
                if (audioLayer == null) {
                    return;
                }

                int heightIndex = Integer.parseInt(ae.getActionCommand()) - 1;

                audioLayer.setHeightIndex(heightIndex);
            };

            for (int i = 0; i < heightItems.length; i++) {
                heightItems[i] = new JMenuItem(Integer.toString(i + 1));
                heightItems[i].addActionListener(al);

                layerHeightMenu.add(heightItems[i]);
            }
            this.add(layerHeightMenu);
        }

        public void setAudioLayer(AudioLayer sLayer) {
            this.audioLayer = sLayer;
            setupHeightMenu();
        }

        private void setupHeightMenu() {
            if (audioLayer == null) {
                return;
            }

            int index = audioLayer.getHeightIndex();

            for (int i = 0; i < heightItems.length; i++) {
                heightItems[i].setEnabled(i != index);
            }
        }
    }
}
