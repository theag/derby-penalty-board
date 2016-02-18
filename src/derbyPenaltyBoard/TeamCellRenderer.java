/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard;

import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author nbp184
 */
public class TeamCellRenderer extends DefaultTableCellRenderer {
    
    public TeamCellRenderer() {
        super.setBorder(BorderFactory.createEmptyBorder(
                FullScreenOptionsDialog.getRowPadding(FullScreenOptionsDialog.TOP),
                FullScreenOptionsDialog.getRowPadding(FullScreenOptionsDialog.LEFT),
                FullScreenOptionsDialog.getRowPadding(FullScreenOptionsDialog.BOTTOM),
                FullScreenOptionsDialog.getRowPadding(FullScreenOptionsDialog.RIGHT)));
        super.setFont(super.getFont().deriveFont(FullScreenOptionsDialog.getCellFontSize()));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setFont(label.getFont().deriveFont(FullScreenOptionsDialog.getCellFontSize()));
        return label;
    }
    
    public FontMetrics getFontMetrics() {
        return super.getFontMetrics(super.getFont());
    }
    
}
