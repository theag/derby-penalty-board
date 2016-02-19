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
    
    public final static int ROW_HEADER_COLUMN = 0;
    public final static int NUMBER_COLUMN = 1;
    public final static int IS_ROSTERED_COLUMN = 2;
    public final static int PENALTY_START_COLUMN = 3;
    public final static int IS_EJECTED_COLUMN = Player.MAX_PENALITIES + 3;
    
    private final Team team;
    
    public TeamTableModel(Team team) {
        this.team = team;
    }

    @Override
    public int getRowCount() {
        return team.players.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return Player.MAX_PENALITIES + 4;
    }
    
    @Override
    public String getColumnName(int column) {
        if(column == ROW_HEADER_COLUMN) {
            return "";
        } else if(column == NUMBER_COLUMN) {
            return "Player";
        } else if(column == IS_ROSTERED_COLUMN) {
            return "Rostered";
        } else if(column == IS_EJECTED_COLUMN) {
            return "Ejected";
        } else {
            return "P" +(column-PENALTY_START_COLUMN);
        }
    }
    
    @Override
    public Class getColumnClass(int column) {
        if(column == ROW_HEADER_COLUMN) {
            return Object.class;
        } else {
            return getValueAt(0, column).getClass();
        }

    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != IS_ROSTERED_COLUMN && columnIndex != IS_EJECTED_COLUMN && columnIndex != ROW_HEADER_COLUMN;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex == team.players.size()) {
            Player p = new Player();
            if(columnIndex == ROW_HEADER_COLUMN) {
                return "*";
            } else if(columnIndex == NUMBER_COLUMN) {
                return p.number;
            } else if(columnIndex == IS_ROSTERED_COLUMN) {
                return p.isRostered;
            } else if(columnIndex == IS_EJECTED_COLUMN) {
                return p.isEjected;
            } else {
                return p.penalties[columnIndex - PENALTY_START_COLUMN];
            }
        } else {
            if(columnIndex == ROW_HEADER_COLUMN) {
                return "";
            } else if(columnIndex == NUMBER_COLUMN) {
                return team.players.get(rowIndex).number;
            } else if(columnIndex == IS_ROSTERED_COLUMN) {
                return team.players.get(rowIndex).isRostered;
            } else if(columnIndex == IS_EJECTED_COLUMN) {
                return team.players.get(rowIndex).isEjected;
            } else {
                return team.players.get(rowIndex).penalties[columnIndex-PENALTY_START_COLUMN];
            }
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(rowIndex == team.players.size()) {
            Player p = new Player();
            if(columnIndex == NUMBER_COLUMN) {
                p.number = (String)aValue;
            } else {
                String penalty = (String)aValue;
                p.penalties[columnIndex-2] = penalty.toUpperCase();
            }
            team.players.add(p);
            fireTableDataChanged();
        } else {
            if(columnIndex == NUMBER_COLUMN) {
                team.players.get(rowIndex).number = (String)aValue;
            } else {
                String penalty = (String)aValue;
                team.players.get(rowIndex).penalties[columnIndex-2] = penalty.toUpperCase();
            }
        }
        TeamUpdateEvent evt = new TeamUpdateEvent(TeamUpdateEvent.TABLE, null, team);
        TeamUpdateEvent.TeamUpdateListener[] listeners = listenerList.getListeners(TeamUpdateEvent.TeamUpdateListener.class);
        for(TeamUpdateEvent.TeamUpdateListener listener : listeners) {
            listener.onTeamUpdate(evt);
        }
    }

    public void sort() {
        team.sortPlayers();
        fireTableDataChanged();
    }
    
    public void addTeamUpdateListener(TeamUpdateEvent.TeamUpdateListener listener) {
        listenerList.add(TeamUpdateEvent.TeamUpdateListener.class, listener);
    }
    
}
