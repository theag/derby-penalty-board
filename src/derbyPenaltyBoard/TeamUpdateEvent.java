/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard;

import java.awt.Component;
import java.util.EventListener;

/**
 *
 * @author nbp184
 */
public class TeamUpdateEvent {
    
    public interface TeamUpdateListener extends EventListener {
        public void onTeamUpdate(TeamUpdateEvent evt);
    }
    
    public static final int COLOUR = 0;
    public static final int TABLE = 1;
    public static final int FORCED = 2;
    public static final int IDENTIFIER = 3;
    
    private int type;
    private Component source;
    private Team team;
    
    public TeamUpdateEvent(int type, Component source, Team team) {
        this.type = type;
        this.source = source;
        this.team = team;
    }
    
    public int getType() {
        return type;
    }
    
    public Component getSource() {
        return source;
    }
    
    public Team getTeam() {
        return team;
    }
    
}
