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
public class Player implements Comparable<Player> {
    
    public static final int MAX_PENALITIES = 7;

    public String number;
    public final Penalty[] penalties;
    public boolean isEjected;

    public Player() {
        number = "";
        penalties = new Penalty[MAX_PENALITIES];
        for(int i = 0; i < MAX_PENALITIES; i++) {
            penalties[i] = new Penalty();
        }
        isEjected = false;
    }

    @Override
    public int compareTo(Player o) {
        if(number.isEmpty() || o.number.isEmpty()) {
            return -number.compareTo(o.number);
        } else {
            return number.compareTo(o.number);
        }
    }
    
    class Penalty {
        public String code;
        public boolean sat;
        
        public Penalty() {
            code = "";
            sat = false;
        }
        
        public String display() {
            return code;
        }
        
    }

}
