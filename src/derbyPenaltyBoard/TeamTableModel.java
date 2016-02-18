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
    
    public final static int IS_EJECTED_COLUMN = Player.MAX_PENALITIES + 1;
    
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
        if(column == 0) {
            return "Player";
        } else if(column == Player.MAX_PENALITIES + 1) {
            return "Ejected";
        } else {
            return "P" +column;
        }
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
        TeamUpdateEvent evt = new TeamUpdateEvent(TeamUpdateEvent.TABLE, null, team);
        TeamUpdateEvent.TeamUpdateListener[] listeners = listenerList.getListeners(TeamUpdateEvent.TeamUpdateListener.class);
        for(TeamUpdateEvent.TeamUpdateListener listener : listeners) {
            listener.onTeamUpdate(evt);
        }
    }

    public void sort() {
        team.sortPlayers();
        this.fireTableDataChanged();
    }
    
    public void addTeamUpdateListener(TeamUpdateEvent.TeamUpdateListener listener) {
        listenerList.add(TeamUpdateEvent.TeamUpdateListener.class, listener);
    }
    
}
