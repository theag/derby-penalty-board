/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard;

import javax.swing.JColorChooser;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author nbp184
 */
public class TeamPanel extends javax.swing.JPanel {

    private Team team;
    private int rowClicked;
    
    /**
     * Creates new form TeamPanel
     */
    public TeamPanel(Team team) {
        initComponents();
        tblTeam.setModel(new TeamTableModel(team));
        tblTeam.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        ((javax.swing.JComponent)tblTeam.getDefaultRenderer(Boolean.class)).setOpaque(true);
        btnColour.setBackground(team.colour);
        txtIdentifier.setText(team.identifier);
        this.team = team;
        rowClicked = -1;
        spnOfficialReviews.setModel(new OfficialReviewSpinnerModel(team));
        spnTimeouts.setModel(new TimeoutSpinnerModel(team));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupPlayer = new javax.swing.JPopupMenu();
        miNumber = new javax.swing.JMenuItem();
        miMoveUp = new javax.swing.JMenuItem();
        miMoveDown = new javax.swing.JMenuItem();
        lblIdentifier = new javax.swing.JLabel();
        txtIdentifier = new javax.swing.JTextField();
        lblColour = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTeam = new javax.swing.JTable();
        btnSort = new javax.swing.JButton();
        btnColour = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        spnOfficialReviews = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        spnTimeouts = new javax.swing.JSpinner();

        miNumber.setText("jMenuItem1");
        miNumber.setEnabled(false);
        popupPlayer.add(miNumber);

        miMoveUp.setText("Move Up");
        miMoveUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miMoveUpActionPerformed(evt);
            }
        });
        popupPlayer.add(miMoveUp);

        miMoveDown.setText("Move Down");
        miMoveDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miMoveDownActionPerformed(evt);
            }
        });
        popupPlayer.add(miMoveDown);

        lblIdentifier.setText("Team Identifier:");

        txtIdentifier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdentifierFocusLost(evt);
            }
        });

        lblColour.setText("Team Colour:");

        tblTeam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblTeam.setRowSelectionAllowed(false);
        tblTeam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblTeamFocusLost(evt);
            }
        });
        tblTeam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTeamMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTeamMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblTeamMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblTeam);

        btnSort.setText("Sort");
        btnSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortActionPerformed(evt);
            }
        });

        btnColour.setText(" ");
        btnColour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColourActionPerformed(evt);
            }
        });

        jLabel1.setText("Official Reviews:");

        jLabel2.setText("Timeouts:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblIdentifier)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdentifier)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblColour)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnColour))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSort)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnOfficialReviews, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnTimeouts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdentifier)
                    .addComponent(txtIdentifier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblColour)
                    .addComponent(btnColour))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(spnOfficialReviews, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(spnTimeouts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSort)
                .addGap(6, 6, 6))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortActionPerformed
        ((TeamTableModel)tblTeam.getModel()).sort();
        fireTeamTableUpdated();
    }//GEN-LAST:event_btnSortActionPerformed

    private void btnColourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColourActionPerformed
        team.colour = JColorChooser.showDialog(this, "Choose Team Colour", btnColour.getBackground());
        btnColour.setBackground(team.colour);
        fireTeamColourUpdated();
    }//GEN-LAST:event_btnColourActionPerformed

    private void txtIdentifierFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdentifierFocusLost
        team.identifier = txtIdentifier.getText();
        fireTeamIdentifierUpdated();
    }//GEN-LAST:event_txtIdentifierFocusLost

    private void tblTeamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTeamMouseReleased
        if(evt.isPopupTrigger() && rowClicked >= 0) {
            miNumber.setText(team.players[rowClicked].number);
            miMoveUp.setEnabled(rowClicked > 0);
            miMoveDown.setEnabled(rowClicked < Team.getMaxRoster() - 1);
            popupPlayer.show(tblTeam, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblTeamMouseReleased

    private void tblTeamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTeamMousePressed
        if(tblTeam.isEditing()) {
            tblTeam.getCellEditor().stopCellEditing();
            fireTeamTableUpdated();
        }
        rowClicked = tblTeam.rowAtPoint(evt.getPoint());
        if(team.players[rowClicked].number.isEmpty()) {
            rowClicked = -1;
        }
    }//GEN-LAST:event_tblTeamMousePressed

    private void tblTeamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTeamMouseClicked
        if(SwingUtilities.isLeftMouseButton(evt)) {
            int column = tblTeam.columnAtPoint(evt.getPoint());
            if(column == TeamTableModel.IS_EJECTED_COLUMN) {
                int row = tblTeam.rowAtPoint(evt.getPoint());
                team.players[row].isEjected = !team.players[row].isEjected;
                ((TeamTableModel)tblTeam.getModel()).fireTableDataChanged();
                fireTeamTableUpdated();
            } else if(TeamTableModel.isSatColumn(column)) {
                int row = tblTeam.rowAtPoint(evt.getPoint());
                team.players[row].penalties[TeamTableModel.getPenaltyIndex(column)].sat = !team.players[row].penalties[TeamTableModel.getPenaltyIndex(column)].sat;
                ((TeamTableModel)tblTeam.getModel()).fireTableDataChanged();
                fireTeamTableUpdated();
            }
        }
    }//GEN-LAST:event_tblTeamMouseClicked

    private void tblTeamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblTeamFocusLost
        if(tblTeam.isEditing()) {
            tblTeam.getCellEditor().stopCellEditing();
            fireTeamTableUpdated();
        }
    }//GEN-LAST:event_tblTeamFocusLost

    private void miMoveUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miMoveUpActionPerformed
        team.movePlayerUp(rowClicked);
        ((TeamTableModel)tblTeam.getModel()).fireTableDataChanged();
        fireTeamTableUpdated();
    }//GEN-LAST:event_miMoveUpActionPerformed

    private void miMoveDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miMoveDownActionPerformed
        team.movePlayerDown(rowClicked);
        ((TeamTableModel)tblTeam.getModel()).fireTableDataChanged();
        fireTeamTableUpdated();
    }//GEN-LAST:event_miMoveDownActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnColour;
    private javax.swing.JButton btnSort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblColour;
    private javax.swing.JLabel lblIdentifier;
    private javax.swing.JMenuItem miMoveDown;
    private javax.swing.JMenuItem miMoveUp;
    private javax.swing.JMenuItem miNumber;
    private javax.swing.JPopupMenu popupPlayer;
    private javax.swing.JSpinner spnOfficialReviews;
    private javax.swing.JSpinner spnTimeouts;
    private javax.swing.JTable tblTeam;
    private javax.swing.JTextField txtIdentifier;
    // End of variables declaration//GEN-END:variables

    public void stopEditing() {
        if(tblTeam.isEditing()) {
            tblTeam.getCellEditor().stopCellEditing();
        }
    }
    
    public void scrimModeChange() {
        ((TeamTableModel)tblTeam.getModel()).fireTableDataChanged();
        ((OfficialReviewSpinnerModel)spnOfficialReviews.getModel()).setMaximum(Team.getMaxReviews());
        if((Integer)spnOfficialReviews.getValue() > Team.getMaxReviews()) {
            spnOfficialReviews.setValue(Team.getMaxReviews());
        }
        ((TimeoutSpinnerModel)spnTimeouts.getModel()).setMaximum(Team.getMaxTimeouts());
        if((Integer)spnTimeouts.getValue() > Team.getMaxTimeouts()) {
            spnTimeouts.setValue(Team.getMaxTimeouts());
        }
        fireTeamTableUpdated();
    }
    
    public void addTeamUpdateListener(TeamUpdateEvent.TeamUpdateListener listener) {
        listenerList.add(TeamUpdateEvent.TeamUpdateListener.class, listener);
        ((TeamTableModel)tblTeam.getModel()).addTeamUpdateListener(listener);
        ((OfficialReviewSpinnerModel)spnOfficialReviews.getModel()).addTeamUpdateListener(listener);
        ((TimeoutSpinnerModel)spnTimeouts.getModel()).addTeamUpdateListener(listener);
    }

    private void fireTeamTableUpdated() {
        TeamUpdateEvent evt = new TeamUpdateEvent(TeamUpdateEvent.TABLE, this, team);
        TeamUpdateEvent.TeamUpdateListener[] listeners = listenerList.getListeners(TeamUpdateEvent.TeamUpdateListener.class);
        for(TeamUpdateEvent.TeamUpdateListener listener : listeners) {
            listener.onTeamUpdate(evt);
        }
    }

    private void fireTeamColourUpdated() {
        TeamUpdateEvent evt = new TeamUpdateEvent(TeamUpdateEvent.COLOUR, this, team);
        TeamUpdateEvent.TeamUpdateListener[] listeners = listenerList.getListeners(TeamUpdateEvent.TeamUpdateListener.class);
        for(TeamUpdateEvent.TeamUpdateListener listener : listeners) {
            listener.onTeamUpdate(evt);
        }
    }

    private void fireTeamIdentifierUpdated() {
        TeamUpdateEvent evt = new TeamUpdateEvent(TeamUpdateEvent.IDENTIFIER, this, team);
        TeamUpdateEvent.TeamUpdateListener[] listeners = listenerList.getListeners(TeamUpdateEvent.TeamUpdateListener.class);
        for(TeamUpdateEvent.TeamUpdateListener listener : listeners) {
            listener.onTeamUpdate(evt);
        }
    }
}
