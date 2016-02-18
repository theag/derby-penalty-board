/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author nbp184
 */
public class FullScreenTable extends JTable {
    
    private Team team;
    
    public FullScreenTable(Team team) {
        this.team = team;
        setModel(new FullScreenTeamTableModel(team));
        setShowGrid(true);
        setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
        getTableHeader().setDefaultRenderer(new TeamHeaderRenderer(team.colour));
        TeamCellRenderer renderer = new TeamCellRenderer();
        setDefaultRenderer(Object.class, renderer);
        setRowHeight(renderer.getFontMetrics().getHeight()
                + FullScreenOptionsDialog.getRowPadding(FullScreenOptionsDialog.TOP)
                + FullScreenOptionsDialog.getRowPadding(FullScreenOptionsDialog.BOTTOM));
        setRowSelectionAllowed(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                tblTeamComponentResized(evt);
            }
        });
    }
    
    public FullScreenTeamTableModel getMyModel() {
        return (FullScreenTeamTableModel)getModel();
    }
    
    private void tblTeamComponentResized(java.awt.event.ComponentEvent evt) {
        TableColumn column = getColumnModel().getColumn(0);
        column.setPreferredWidth(column.getPreferredWidth()*2);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(new BasicStroke(FullScreenOptionsDialog.getEjectedLineThickness()));
        }
        g.setColor(Color.black);
        for(int i = 0; i < team.getActivePlayerCount(); i++) {
            if(team.getActivePlayer(i).isEjected) {
                Rectangle r1 = getCellRect(i, 0, true);
                Rectangle r2 = getCellRect(i, Player.MAX_PENALITIES, true);
                g.drawLine(r1.x + FullScreenOptionsDialog.getRowPadding(FullScreenOptionsDialog.LEFT), r1.y + r1.height/2,
                        r2.x + r2.width - FullScreenOptionsDialog.getRowPadding(FullScreenOptionsDialog.RIGHT), r2.y + r2.height/2);
            }
        }
    }
    
}
