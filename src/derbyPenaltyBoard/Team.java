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
import java.util.ArrayList;
import java.util.Collections;

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
        while(line != null) {
            rv.players.add(new Player(line));
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
    
    public String identifier;
    public Color colour;
    public final ArrayList<Player> players;
    
    public Team(boolean isBlack) {
        if(isBlack) {
            identifier = "Black";
            colour = Color.black;
        } else {
            identifier = "White";
            colour = Color.white;
        }
        players = new ArrayList<Player>(MAX_ROSTER);
    }
    
    private Team(String identifier) {
        this.identifier = identifier;
        this.colour = Color.white;
        players = new ArrayList<Player>(MAX_ROSTER);
    }
    
    public void sortPlayers() {
        Collections.sort(players);
    }

    public int getActivePlayerCount() {
        int count = 0;
        for(Player player : players) {
            if(!player.number.trim().isEmpty() && player.isRostered) {
                count++;
            }
        }
        if(count > MAX_ROSTER) {
            return MAX_ROSTER;
        } else {
            return count;
        }
    }

    public Player getActivePlayer(int index) {
        int count = 0;
        for(Player player : players) {
            if (!player.number.trim().isEmpty() && player.isRostered) {
                if(count == index) {
                    return player;
                } else {
                    count++;
                }
            }
        }
        return null;
    }

    public void movePlayerUp(int index) {
        Player player = players.remove(index);
        players.add(index-1, player);
    }

    public void movePlayerDown(int index) {
        Player player = players.remove(index);
        players.add(index+1, player);
    }

    public Color getContrast() {
        return Team.getContrast(colour);
    }
    
}
