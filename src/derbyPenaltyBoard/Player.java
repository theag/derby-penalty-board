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
    public final String[] penalties;
    public boolean isEjected;
    public boolean isRostered;

    public Player() {
        number = "";
        penalties = new String[MAX_PENALITIES];
        for(int i = 0; i < MAX_PENALITIES; i++) {
            penalties[i] = "";
        }
        isEjected = false;
        isRostered = true;
    }

    public Player(String number) {
        this.number = number;
        penalties = new String[MAX_PENALITIES];
        for(int i = 0; i < MAX_PENALITIES; i++) {
            penalties[i] = "";
        }
        isEjected = false;
        isRostered = true;
    }

    @Override
    public int compareTo(Player o) {
        if(number.isEmpty() || o.number.isEmpty()) {
            if(isRostered && !o.isRostered) {
                return 1;
            } else if(!isRostered && o.isRostered) {
                return -1;
            } else {
                return -number.compareTo(o.number);
            }
        } else {
            if(isRostered && !o.isRostered) {
                return -1;
            } else if(!isRostered && o.isRostered) {
                return 1;
            } else {
                return number.compareTo(o.number);
            }
        }
    }

}
