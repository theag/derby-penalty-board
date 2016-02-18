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

    public static Team load(File file) throws IOException {
        BufferedReader inFile = new BufferedReader(new FileReader(file));
        Team rv = new Team(inFile.readLine());
        String colour = inFile.readLine();
        if(!colour.isEmpty()) {
            try {
                rv.colour = Color.decode(colour);
            } catch(NumberFormatException ex) {
                throw new IOException(ex.getMessage());
            }
        }
        String line = inFile.readLine();
        int index = 0;
        while(line != null && index < MAX_ROSTER) {
            rv.players[index].number = line;
            line = inFile.readLine();
        }
        inFile.close();
        return rv;
    }

    public static void save(Team team, File file) throws IOException {
        PrintWriter outFile = new PrintWriter(file);
        outFile.println(team.identifier);
        String colour = Integer.toHexString(team.colour.getRGB());
        while(colour.length() < 6) {
            colour = "0" +colour;
        }
        outFile.println("#" +colour);
        for(Player p : team.players) {
            outFile.println(p.number);
        }
        outFile.close();
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
    
}
