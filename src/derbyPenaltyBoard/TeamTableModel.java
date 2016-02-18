package derbyPenaltyBoard;


import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nbp184
 */
public class TeamTableModel extends AbstractTableModel {
    
    private static final String[] columnNames = {"Player", "P1", "P2", "P3", "P4", "P5", "P6", "P7", "Is Ejected"};
    //private static final Class[] columnClasses = {String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Boolean.class};
    
    private final Team team;
    
    public TeamTableModel(Team team) {
        this.team = team;
    }

    @Override
    public int getRowCount() {
        return Team.MAX_ROSTER;
    }

    @Override
    public int getColumnCount() {
        return Player.MAX_PENALITIES + 2;
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();

    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return  columnIndex != Player.MAX_PENALITIES + 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0) {
            return team.players[rowIndex].number;
        } else if(columnIndex == Player.MAX_PENALITIES + 1) {
            return team.players[rowIndex].isEjected;
        } else {
            return team.players[rowIndex].penalties[columnIndex-1];
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(columnIndex == 0) {
            team.players[rowIndex].number = (String)aValue;
        } else {
            String penalty = (String)aValue;
            team.players[rowIndex].penalties[columnIndex-1] = penalty.toUpperCase();
        }
    }

    public void sort() {
        team.sortPlayers();
        this.fireTableDataChanged();
    }
    
}
