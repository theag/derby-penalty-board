/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author nbp184
 */
public class TeamHeaderRenderer implements TableCellRenderer {
    
    private Color colour;
    private Color contrast;
    
    public TeamHeaderRenderer(Color colour) {
        this.colour = colour;
        contrast = pickContrastForeground(colour);
        
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel rv = new JLabel((String)value);
        rv.setFont(rv.getFont().deriveFont(FullScreenOptionsDialog.getHeaderFontSize()));
        rv.setBackground(colour);
        if(table.getRowCount() == 0) {
            if(column == 0) {
                rv.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black), BorderFactory.createEmptyBorder(
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.TOP),
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.LEFT),
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.BOTTOM),
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.RIGHT))));
            } else {
                rv.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.black), BorderFactory.createEmptyBorder(
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.TOP),
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.LEFT),
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.BOTTOM),
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.RIGHT))));
            }
        } else {
            if(column == 0) {
                rv.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.black), BorderFactory.createEmptyBorder(
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.TOP),
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.LEFT),
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.BOTTOM),
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.RIGHT))));
            } else {
                rv.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.black), BorderFactory.createEmptyBorder(
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.TOP),
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.LEFT),
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.BOTTOM),
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.RIGHT))));
            }
        }
        if(column == 0 && FullScreenOptionsDialog.isShowingTeamIdentifier()) {
            rv.setForeground(contrast);
        } else {
            rv.setForeground(colour);
        }
        rv.setOpaque(true);
        return rv;
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
    
}
