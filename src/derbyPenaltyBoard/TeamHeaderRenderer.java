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
        contrast = Team.getContrast(colour);
        
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel rv = new JLabel((String)value);
        rv.setFont(rv.getFont().deriveFont(FullScreenOptionsDialog.getHeaderFontSize()));
        rv.setBackground(colour);
        rv.setBorder(BorderFactory.createEmptyBorder(
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.TOP),
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.LEFT),
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.BOTTOM),
                        FullScreenOptionsDialog.getHeaderPadding(FullScreenOptionsDialog.RIGHT)));
        if(column == 0 && FullScreenOptionsDialog.isShowingTeamIdentifier()) {
            rv.setForeground(contrast);
        } else {
            rv.setForeground(colour);
        }
        rv.setOpaque(true);
        return rv;
    }
    
}
