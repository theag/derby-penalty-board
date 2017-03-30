/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard;

import javax.swing.SpinnerNumberModel;

/**
 *
 * @author nbp184
 */
public class TimeoutSpinnerModel extends SpinnerNumberModel {
    
    private final Team team;
    
    public TimeoutSpinnerModel(Team team) {
        super(team.timeouts, 0, Team.MAX_TIMEOUTS, 1);
        this.team = team;
    }
    
    @Override
    public void setValue(Object value) {
        super.setValue(value);
        team.timeouts = (Integer)value;
        TeamUpdateEvent evt = new TeamUpdateEvent(TeamUpdateEvent.SPINNER, null, team);
        TeamUpdateEvent.TeamUpdateListener[] listeners = listenerList.getListeners(TeamUpdateEvent.TeamUpdateListener.class);
        for(TeamUpdateEvent.TeamUpdateListener listener : listeners) {
            listener.onTeamUpdate(evt);
        }
    }
    
    public void addTeamUpdateListener(TeamUpdateEvent.TeamUpdateListener listener) {
        listenerList.add(TeamUpdateEvent.TeamUpdateListener.class, listener);
    }
    
}
