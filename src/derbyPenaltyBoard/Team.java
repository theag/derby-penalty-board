/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard;

import java.awt.Color;

/**
 *
 * @author nbp184
 */
public class Team {
    public static final int MAX_ROSTER = 14;
    
    public String identifier;
    public Color colour;
    public final Player[] players;
    
    public Team(boolean isBlack) {
        if(isBlack) {
            identifier = "Black";
            colour = Color.black;
        } else {
            identifier = "White";
            colour = Color.white;
        }
        players = new Player[MAX_ROSTER];
        for(int i = 0; i < MAX_ROSTER; i++) {
            players[i] = new Player();
        }
    }
    
    public void sortPlayers() {
        Player temp;
        int j;
        for(int i = 1; i < MAX_ROSTER; i++) {
            j = i;
            while(j > 0 && players[j-1].compareTo(players[j]) > 0) {
                temp = players[j-1];
                players[j-1] = players[j];
                players[j] = temp;
                j--;
            }
        }
    }

    public int getActivePlayerCount() {
        int count = 0;
        for(int i = 0; i < players.length; i++) {
            if(!players[i].number.trim().isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public Player getActivePlayer(int index) {
        int count = 0;
        for(int i = 0; i < players.length; i++) {
            if(!players[i].number.trim().isEmpty()) {
                if(count == index) {
                    return players[i];
                } else {
                    count++;
                }
            }
        }
        return null;
    }
    
}
