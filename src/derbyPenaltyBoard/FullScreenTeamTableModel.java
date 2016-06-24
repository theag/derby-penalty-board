/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nbp184
 */
public class FullScreenTeamTableModel extends AbstractTableModel {
    
    private static final String[] columnNames = {"Player", "P1", "P2", "P3", "P4", "P5", "P6", "P7"};
    
    private final Team team;
    
    public FullScreenTeamTableModel(Team team) {
        this.team = team;
    }

    @Override
    public int getRowCount() {
        return team.getActivePlayerCount();
    }

    @Override
    public int getColumnCount() {
        return Player.MAX_PENALITIES + 1;
    }
    
    @Override
    public String getColumnName(int column) {
        if(column == 0) {
            return team.identifier;
        } else {
            return columnNames[column];
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 0) {
            return false;
        } else {
            return !team.getActivePlayer(rowIndex).isEjected;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0) {
            return team.getActivePlayer(rowIndex).number;
        } else {
            return team.getActivePlayer(rowIndex).penalties[columnIndex-1];
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String penalty = (String)aValue;
        team.getActivePlayer(rowIndex).penalties[columnIndex-1] = penalty.toUpperCase();
        for(TableModelListener listener : getTableModelListeners()) {
            listener.tableChanged(null);
        }
    }
    
}
