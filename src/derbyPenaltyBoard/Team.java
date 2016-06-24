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
import java.io.PrintWriter;

/**
 *
 * @author nbp184
 */
public class Team {
    public static final int MAX_ROSTER = 14;

    public static Team load(File file) throws IOException, NumberFormatException {
        BufferedReader inFile = new BufferedReader(new FileReader(file));
        Team rv = new Team(inFile.readLine());
        String colour = inFile.readLine();
        if(!colour.isEmpty()) {
            rv.colour = Color.decode(colour);
        }
        String line = inFile.readLine();
        int index = 0;
        while(line != null && index < MAX_ROSTER) {
            rv.players[index++].number = line;
            line = inFile.readLine();
        }
        inFile.close();
        return rv;
    }

    public static void save(Team team, File file) throws IOException {
        PrintWriter outFile = new PrintWriter(file);
        outFile.println(team.identifier);
        String colour = Integer.toHexString(team.colour.getRGB()).substring(2).toUpperCase();
        while(colour.length() < 6) {
            colour = "0" +colour;
        }
        outFile.println("#" +colour);
        for(Player p : team.players) {
            outFile.println(p.number);
        }
        outFile.close();
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
    
    public static String getHTMLColour(Color colour) {
        return "#" +Integer.toHexString(colour.getRGB()).substring(2);
    }
    
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
    
    private Team(String identifier) {
        this.identifier = identifier;
        this.colour = Color.white;
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

    String siteString() {
        String rv = "{identifier: \"" +this.identifier +"\", color: \"" +getHTMLColour(colour) +"\", text: \"" +getHTMLColour(getContrast()) +"\", players: [";
        boolean first = true;
        for(int i = 0; i < players.length; i++) {
            if(!players[i].number.trim().isEmpty()) {
                if(first) {
                   first = false; 
                } else {
                    rv += ", ";
                }
                rv += players[i].siteString();
            }
        }
        rv += "]}";
        return rv;
    }
    
}
