/*
 * blue - object composition environment for csound
 * Copyright (c) 2000-2007 Steven Yi (stevenyi@gmail.com)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by  the Free Software Foundation; either version 2 of the License or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; see the file COPYING.LIB.  If not, write to
 * the Free Software Foundation Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307 USA
 */

package blue.ui.core.score.soundLayer;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JViewport;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import blue.BlueSystem;
import blue.SoundLayer;
import blue.components.IconFactory;
import blue.noteProcessor.NoteProcessorChainMap;
import blue.soundObject.PolyObject;

/**
 * 
 * @author steven
 */
public class SoundLayerListPanel extends javax.swing.JPanel implements
        AdjustmentListener {

    static Point posSync = new Point();

    private PolyObject pObj;

    LayersPanel layers = new LayersPanel();

    JViewport lView = new JViewport();

    LayerHeightPopup heightPopup = null;

    /** Creates new form SoundLayerListPanel2 */
    public SoundLayerListPanel() {
        initComponents();

        this.add(lView, BorderLayout.CENTER);

        lView.setView(layers);
        lView.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                layers.setSize(lView.getWidth(), layers.getHeight());
            }
        });
    }

    public void setPolyObject(PolyObject pObj) {
        this.pObj = pObj;

        layers.setPolyObject(pObj);
    }

    public void addLayer() {
        if (pObj == null) {
            return;
        }

        SelectionModel selection = layers.getSelectionModel();

        int end = selection.getEndIndex();

        if (end < 0) {
            end = pObj.getSize();
        } else {
            end++;
        }
        SoundLayer temp = new SoundLayer();
        temp.setHeightIndex(pObj.getDefaultHeightIndex());

        pObj.addSoundLayer(end, temp);
    }

    public void removeLayer() {
        if (pObj == null) {
            return;
        }

        SelectionModel selection = layers.getSelectionModel();

        int start = selection.getStartIndex();
        int end = selection.getEndIndex();

        if (end < 0 || pObj.getSize() < 2) {
            return;
        }

        int len = (end - start) + 1;

        String message = BlueSystem
                .getString("soundLayerEditPanel.delete.message1")
                + " "
                + len
                + " "
                + BlueSystem.getString("soundLayerEditPanel.delete.message2");
        if (JOptionPane.showConfirmDialog(null, message) == JOptionPane.OK_OPTION) {
            pObj.removeLayers(start, end);
        }
    }

    public void pushUpLayer() {
        if (pObj == null) {
            return;
        }

        SelectionModel selection = layers.getSelectionModel();

        int start = selection.getStartIndex();
        int end = selection.getEndIndex();

        if (end < 0 || start == 0) {
            return;
        }

        pObj.pushUpLayers(start, end);
    }

    public void pushDownLayer() {
        if (pObj == null) {
            return;
        }

        SelectionModel selection = layers.getSelectionModel();

        int start = selection.getStartIndex();
        int end = selection.getEndIndex();

        if (end < 0 || end >= pObj.getSize() - 1) {
            return;
        }

        pObj.pushDownLayers(start, end);
    }

    public void setNoteProcessorChainMap(NoteProcessorChainMap npcMap) {
        layers.setNoteProcessorChainMap(npcMap);
    }

    /* ADJUSTMENT LISTENER */
    public void adjustmentValueChanged(AdjustmentEvent ae) {
        int pos = ae.getValue();
        posSync.setLocation(0, pos);
        lView.setViewPosition(posSync);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        layerHeightButton = new javax.swing.JButton();
        bottomPanel = new javax.swing.JPanel();
        buttonUp = new javax.swing.JButton();
        buttonDown = new javax.swing.JButton();
        buttonAdd = new javax.swing.JButton();
        buttonRemove = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        topPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        topPanel.setMaximumSize(new java.awt.Dimension(32767, 20));
        topPanel.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sound Layers");

        layerHeightButton.setIcon(IconFactory.getDownArrowIcon());
        layerHeightButton.setFocusPainted(false);
        layerHeightButton.setFocusable(false);
        layerHeightButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        layerHeightButton.setMaximumSize(new java.awt.Dimension(17, 16));
        layerHeightButton.setPreferredSize(new java.awt.Dimension(17, 16));
        layerHeightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                layerHeightButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(layerHeightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(layerHeightButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(topPanel, java.awt.BorderLayout.NORTH);

        bottomPanel.setMaximumSize(new java.awt.Dimension(32767, 17));
        bottomPanel.setPreferredSize(new java.awt.Dimension(100, 17));
        bottomPanel.setLayout(new java.awt.GridLayout(1, 0));

        buttonUp.setText("^");
        buttonUp.setFocusPainted(false);
        buttonUp.setFocusable(false);
        buttonUp.setMargin(new java.awt.Insets(0, 1, 0, 1));
        buttonUp.setMinimumSize(new java.awt.Dimension(15, 15));
        buttonUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpActionPerformed(evt);
            }
        });
        bottomPanel.add(buttonUp);

        buttonDown.setText("V");
        buttonDown.setFocusPainted(false);
        buttonDown.setFocusable(false);
        buttonDown.setMargin(new java.awt.Insets(0, 1, 1, 1));
        buttonDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDownActionPerformed(evt);
            }
        });
        bottomPanel.add(buttonDown);

        buttonAdd.setText("+");
        buttonAdd.setFocusPainted(false);
        buttonAdd.setFocusable(false);
        buttonAdd.setMargin(new java.awt.Insets(0, 1, 1, 1));
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });
        bottomPanel.add(buttonAdd);

        buttonRemove.setText("-");
        buttonRemove.setFocusPainted(false);
        buttonRemove.setFocusable(false);
        buttonRemove.setMargin(new java.awt.Insets(0, 1, 1, 1));
        buttonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveActionPerformed(evt);
            }
        });
        bottomPanel.add(buttonRemove);

        add(bottomPanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void layerHeightButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_layerHeightButtonActionPerformed
        if (heightPopup == null) {
            heightPopup = new LayerHeightPopup();
        }

        heightPopup.show(this, layerHeightButton.getX(), layerHeightButton
                .getY()
                + layerHeightButton.getHeight());
    }// GEN-LAST:event_layerHeightButtonActionPerformed

    private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buttonRemoveActionPerformed
        removeLayer();
    }// GEN-LAST:event_buttonRemoveActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buttonAddActionPerformed
        addLayer();
    }// GEN-LAST:event_buttonAddActionPerformed

    private void buttonDownActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buttonDownActionPerformed
        pushDownLayer();
    }// GEN-LAST:event_buttonDownActionPerformed

    private void buttonUpActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buttonUpActionPerformed
        pushUpLayer();
    }// GEN-LAST:event_buttonUpActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonDown;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JButton buttonUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton layerHeightButton;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    private class LayerHeightPopup extends JPopupMenu {

        JMenuItem[] heightItems = new JMenuItem[9];

        public LayerHeightPopup() {
            super();

            ActionListener allListener = new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    if (pObj == null) {
                        return;
                    }

                    int heightIndex = Integer.parseInt(ae.getActionCommand()) - 1;

                    for (int i = 0; i < pObj.getSize(); i++) {
                        SoundLayer temp = (SoundLayer) pObj.getElementAt(i);

                        temp.setHeightIndex(heightIndex);
                    }
                }
            };

            ActionListener defaultListener = new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    if (pObj == null) {
                        return;
                    }

                    int heightIndex = Integer.parseInt(ae.getActionCommand()) - 1;

                    pObj.setDefaultHeightIndex(heightIndex);
                }
            };

            JMenu setAllMenu = new JMenu("Set All Layer Heights");

            for (int i = 0; i < heightItems.length; i++) {
                JMenuItem item = new JMenuItem(Integer.toString(i + 1));
                item.addActionListener(allListener);

                setAllMenu.add(item);
            }

            JMenu setDefaultMenu = new JMenu("Set Default Layer Height");

            for (int i = 0; i < heightItems.length; i++) {
                heightItems[i] = new JMenuItem(Integer.toString(i + 1));
                heightItems[i].addActionListener(defaultListener);

                setDefaultMenu.add(heightItems[i]);
            }

            this.add(setAllMenu);
            this.add(setDefaultMenu);

            this.addPopupMenuListener(new PopupMenuListener() {
                public void popupMenuCanceled(PopupMenuEvent e) {
                }

                public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                }

                public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                    if (pObj == null) {
                        return;
                    }

                    int defaultHeightIndex = pObj.getDefaultHeightIndex();

                    for (int i = 0; i < heightItems.length; i++) {
                        heightItems[i].setEnabled(i != defaultHeightIndex);
                    }
                }
            });
        }
    }
}
