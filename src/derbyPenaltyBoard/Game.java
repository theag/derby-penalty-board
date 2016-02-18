/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard;

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
    
}
