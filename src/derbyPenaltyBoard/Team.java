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
    private static final int MAX_ROSTER = 15;
    private static final int MAX_REVIEWS= 1;
    private static final int MAX_TIMEOUTS = 3;
    
    public static boolean scrimmageMode = false;
    
    public static int getMaxRoster() {
        if(scrimmageMode) {
            return ScrimModeOptionsDialog.getNumberOfPlayers();
        } else {
            return MAX_ROSTER;
        }
    }
    
    public static int getMaxReviews() {
        if(scrimmageMode) {
            return ScrimModeOptionsDialog.getOfficialReviews();
        } else {
            return MAX_REVIEWS;
        }
    }
    
    public static int getMaxTimeouts() {
        if(scrimmageMode) {
            return ScrimModeOptionsDialog.getTimeouts();
        } else {
            return MAX_TIMEOUTS;
        }
    }

    public static Color getContrast(Color colour) {
        int black = 0;
        int white = 0xFF;
        int brightness = (colour.getRed()*299 + colour.getGreen()*587 + colour.getBlue()*114)/1000;
        if(Math.abs(black - brightness) >= Math.abs(white - brightness)) {
            return Color.black;
        } else {
            return Color.white;
        }
    }
    
    public static String getMaxReviewsString() {
        String rv = "";
        for(int i = 0; i < getMaxReviews(); i++) {
            rv += FullScreenOptionsDialog.getOfficialReviewString();
        }
        return rv;
    }
    
    public static String getMaxTimeoutString() {
        String rv = "";
        for(int i = 0; i < getMaxTimeouts(); i++) {
            rv += FullScreenOptionsDialog.getTimeoutString();
        }
        return rv;
    }
    
    public String identifier;
    public Color colour;
    public Player[] players;
    public int officialReviews;
    public int timeouts;
    
    public Team(boolean isBlack) {
        if(isBlack) {
            identifier = "Black";
            colour = Color.black;
        } else {
            identifier = "White";
            colour = Color.white;
        }
        players = new Player[getMaxRoster()];
        for(int i = 0; i < getMaxRoster(); i++) {
            players[i] = new Player();
        }
        officialReviews = getMaxReviews();
        timeouts = getMaxTimeouts();
    }
    
    public void sortPlayers() {
        Player temp;
        int j;
        for(int i = 1; i < getMaxRoster(); i++) {
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

    public void movePlayerUp(int index) {
        Player temp = players[index-1];
        players[index-1] = players[index];
        players[index] = temp;
    }

    public void movePlayerDown(int index) {
        Player temp = players[index+1];
        players[index+1] = players[index];
        players[index] = temp;
    }

    public Color getContrast() {
        return Team.getContrast(colour);
    }
    
    public String getReviewsString() {
        String rv = "";
        for(int i = 0; i < officialReviews; i++) {
            rv += FullScreenOptionsDialog.getOfficialReviewString();
        }
        return rv;
    }
    
    public String getTimeoutString() {
        String rv = "";
        for(int i = 0; i < timeouts; i++) {
            rv += FullScreenOptionsDialog.getTimeoutString();
        }
        return rv;
    }

    public void updateRoster() {
        if(players.length > getMaxRoster()) {
            Player[] temp = players;
            players = new Player[getMaxRoster()];
            System.arraycopy(temp, 0, players, 0, getMaxRoster());
        } else if(players.length < getMaxRoster()) {
            Player[] temp = players;
            players = new Player[getMaxRoster()];
            System.arraycopy(temp, 0, players, 0, temp.length);
            for(int i = temp.length; i < getMaxRoster(); i++) {
                players[i] = new Player();
            }
        }
    }
    
}
