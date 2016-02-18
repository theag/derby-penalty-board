/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author nbp184
 */
public class FullScreenForm extends javax.swing.JFrame {

    private MainFrame mainFrame;
    
    /**
     * Creates new form FullScreenForm
     */
    public FullScreenForm(Game game, MainFrame mainFrame) {
        initComponents();
        
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), "Cancel"); //$NON-NLS-1$
        getRootPane().getActionMap().put("Cancel", new AbstractAction(){ //$NON-NLS-1$
            @Override
            public void actionPerformed(ActionEvent e)
            {
                exitFullScreen();
            }
        });
        
        splitPaneTeam.setLeftComponent(new FullScreenPanel(game.leftTeam));
        splitPaneTeam.setRightComponent(new FullScreenPanel(game.rightTeam));
        
        this.mainFrame = mainFrame;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPaneTeam = new javax.swing.JSplitPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        splitPaneTeam.setDividerSize(0);
        splitPaneTeam.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                splitPaneTeamComponentResized(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPaneTeam, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPaneTeam, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void splitPaneTeamComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_splitPaneTeamComponentResized
        splitPaneTeam.setDividerLocation(0.5);
    }//GEN-LAST:event_splitPaneTeamComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane splitPaneTeam;
    // End of variables declaration//GEN-END:variables

    private void tblTeamComponentResized(java.awt.event.ComponentEvent evt) {
        JTable tbl = (JTable)evt.getComponent();
        TableColumn column = tbl.getColumnModel().getColumn(0);
        column.setPreferredWidth(column.getPreferredWidth()*2);
    }
    
    private void exitFullScreen() {
        GraphicsDevice defaultScreenDevice = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        defaultScreenDevice.setFullScreenWindow(null);
        this.setVisible(false);
        mainFrame.fullScreenWindowClosing(this);
    }

    private Color pickContrastForeground(Color colour) {
        int black = 0;
        int white = 0xFF;
        int brightness = (colour.getRed()*299 + colour.getGreen()*587 + colour.getBlue()*114)/1000;
        if(Math.abs(black - brightness) >= Math.abs(white - brightness)) {
            return Color.black;
        } else {
            return Color.white;
        }
    }

    public void updateTables() {
        ((FullScreenPanel)splitPaneTeam.getLeftComponent()).updateAll();
        ((FullScreenPanel)splitPaneTeam.getRightComponent()).updateAll();
    }

    public void leftTeamUpdate(TeamUpdateEvent evt) {
        switch(evt.getType()) {
            case TeamUpdateEvent.TABLE:
                ((FullScreenPanel)splitPaneTeam.getLeftComponent()).updateTable();
                break;
            case TeamUpdateEvent.COLOUR:
            case TeamUpdateEvent.IDENTIFIER:
                ((FullScreenPanel)splitPaneTeam.getLeftComponent()).updateHeader();
                break;
        }
    }

    public void rightTeamUpdate(TeamUpdateEvent evt) {
        switch(evt.getType()) {
            case TeamUpdateEvent.TABLE:
                ((FullScreenPanel)splitPaneTeam.getRightComponent()).updateTable();
                break;
            case TeamUpdateEvent.COLOUR:
            case TeamUpdateEvent.IDENTIFIER:
                ((FullScreenPanel)splitPaneTeam.getRightComponent()).updateHeader();
                break;
        }
    }
}
