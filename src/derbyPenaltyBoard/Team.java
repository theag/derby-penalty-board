/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author nbp184
 */
public class Team {
    public static final int MAX_ROSTER = 14;
    public static final int MAX_REVIEWS = 1;
    public static final int MAX_TIMEOUTS = 3;

    public static Team load(File file, boolean isBlack) throws IOException, NumberFormatException {
        BufferedReader inFile = new BufferedReader(new FileReader(file));
        Team rv = new Team(isBlack);
        String line = inFile.readLine().trim();
        int index = 0;
        while(line != null && index < MAX_ROSTER) {
            if(!line.isEmpty()) {
                int space = line.indexOf(" ");
                if(space >= 0) {
                    rv.players[index++].number = line.substring(0, space);
                } else {
                    rv.players[index++].number = line;
                }
            }
            line = inFile.readLine();
        }
        inFile.close();
        return rv;
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
        for(int i = 0; i < MAX_REVIEWS; i++) {
            rv += FullScreenOptionsDialog.getOfficialReviewString();
        }
        return rv;
    }
    
    public static String getMaxTimeoutString() {
        String rv = "";
        for(int i = 0; i < MAX_TIMEOUTS; i++) {
            rv += FullScreenOptionsDialog.getTimeoutString();
        }
        return rv;
    }
    
    public String identifier;
    public Color colour;
    public final Player[] players;
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
        players = new Player[MAX_ROSTER];
        for(int i = 0; i < MAX_ROSTER; i++) {
            players[i] = new Player();
        }
        officialReviews = MAX_REVIEWS;
        timeouts = MAX_TIMEOUTS;
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
    
}
