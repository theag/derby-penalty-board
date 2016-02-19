/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author nbp184
 */
public class TeamCellRenderer extends DefaultTableCellRenderer  {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(column == TeamTableModel.ROW_HEADER_COLUMN) {
            Component c = table.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if(c instanceof JLabel) {
                ((JLabel)c).setHorizontalAlignment(JLabel.TRAILING);
                if(isSelected) {
                    ((JLabel)c).setText(">");
                }
            }
            return c;
        } else if(row == table.getRowCount()-1 && (column == TeamTableModel.IS_EJECTED_COLUMN || column == TeamTableModel.IS_ROSTERED_COLUMN)) {
            return super.getTableCellRendererComponent(table, "", isSelected, hasFocus, row, column);
        } else {
            if(column == TeamTableModel.IS_EJECTED_COLUMN || column == TeamTableModel.IS_ROSTERED_COLUMN) {
                JCheckBox cb = new JCheckBox("", (Boolean)value);
                cb.setHorizontalAlignment(JCheckBox.CENTER);
                Component c = super.getTableCellRendererComponent(table, "", isSelected, hasFocus, row, column);
                cb.setBackground(c.getBackground());
                cb.setOpaque(c.isOpaque());
                return cb;
            } else {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        }
    }
    
}
