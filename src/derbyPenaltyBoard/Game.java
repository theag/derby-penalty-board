/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nbp184
 */
public class Game {
    
    public Team leftTeam;
    public Team rightTeam;
    
    public Game() {
        leftTeam = new Team(false);
        rightTeam = new Team(true);
    }

    public void teamSwap() {
        Team t = leftTeam;
        leftTeam = rightTeam;
        rightTeam = t;
    }

    void writeSite() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new File("C:\\Users\\nbp184\\Documents\\HTML Sites\\derby penalty board\\game.js"));
            out.println("var teams = [" +leftTeam.siteString() +"," +rightTeam.siteString() +"];");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    String siteString() {
        return "teams = [" +leftTeam.siteString() +"," +rightTeam.siteString() +"];";
    }
    
}
