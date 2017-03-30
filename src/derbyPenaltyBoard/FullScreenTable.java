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
        setTableHeader(null);
        TeamCellRenderer renderer = new TeamCellRenderer();
        setDefaultRenderer(Object.class, renderer);
        setRowHeight(renderer.getFontMetrics().getHeight()
                + FullScreenOptionsDialog.getRowPadding(FullScreenOptionsDialog.TOP)
                + FullScreenOptionsDialog.getRowPadding(FullScreenOptionsDialog.BOTTOM));
        setRowSelectionAllowed(false);
        if(FullScreenOptionsDialog.isPlayerColumnWidthSet()) {
            this.getColumnModel().getColumn(0).setPreferredWidth(FullScreenOptionsDialog.getPlayerColumnWidth());
        }
    }
    
    public FullScreenTeamTableModel getMyModel() {
        return (FullScreenTeamTableModel)getModel();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = null;
        BasicStroke dot = null;
        BasicStroke ejected = null;
        if(g instanceof Graphics2D) {
            g2 = (Graphics2D)g;
            dot = new BasicStroke();
            ejected = new BasicStroke(FullScreenOptionsDialog.getEjectedLineThickness());
            g2.setStroke(dot);
        }
        g.setColor(Color.black);
        Player player;
        for(int i = 0; i < team.getActivePlayerCount(); i++) {
            player = team.getActivePlayer(i);
            for(int p = 0; p < Player.MAX_PENALITIES; p++) {
                if(player.penalties[p].sat) {
                    Rectangle r1 = getCellRect(i, p+1, true);
                    int dotHeight = 5;
                    switch(FullScreenOptionsDialog.getSatDotHeightUnit()) {
                        case 'F':
                            dotHeight = Math.round(r1.height*FullScreenOptionsDialog.getSatDotHeight_Fraction());
                            break;
                        case 'P':
                            dotHeight = FullScreenOptionsDialog.getSatDotHeight_Pixel();
                            break;
                    }
                    g.drawOval(r1.x + r1.width - dotHeight - 3, r1.y + r1.height - dotHeight - 3, dotHeight, dotHeight);
                    g.fillOval(r1.x + r1.width - dotHeight - 3, r1.y + r1.height - dotHeight - 3, dotHeight, dotHeight);
                }
            }
            if(player.isEjected) {
                Rectangle r1 = getCellRect(i, 0, true);
                Rectangle r2 = getCellRect(i, Player.MAX_PENALITIES, true);
                if(g2 != null) {
                    g2.setStroke(ejected);
                }
                g.drawLine(r1.x + FullScreenOptionsDialog.getRowPadding(FullScreenOptionsDialog.LEFT), r1.y + r1.height/2,
                        r2.x + r2.width - FullScreenOptionsDialog.getRowPadding(FullScreenOptionsDialog.RIGHT), r2.y + r2.height/2);
                if(g2 != null) {
                    g2.setStroke(dot);
                }
            }
        }
    }
    
}
