/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author nbp184
 */
public class PrintPenaltyCodes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        String[][] penaltyCodes = new String[][]{
            {"B", "Back Block"},
            {"A", "High Block"},
            {"L", "Low Block"},
            {"E", "Elbows"},
            {"F", "Forearms"},
            {"H", "Blocking w/ Head"},
            {"M", "Multi-Player"},
            {"O", "Out Of Bounds Block/Assist"},
            {"C", "Direction of Play"},
            {"C", "Clockwise Block/Assist"},
            {"C", "Stopped Block/Assist"},
            {"P", "Out of Play"},
            {"P", "Destroying the Pack"},
            {"P", "Failure to Reform/Return"},
            {"P", "Illegal Return"},
            {"X", "Cutting"},
            {"S", "Skating Out of Bounds"},
            {"I", "Illegal Procedure"},
            {"I", "Failure to Yield"},
            {"I", "Illegal Positioning"},
            {"I", "Penalty Box Violation"},
            {"I", "Equipment Violation"},
            {"I", "Uniform Violation"},
            {"I", "Star Pass Violation"},
            {"I", "Bench Staff Violation"},
            {"I", "Illegal Call-off"},
            {"I", "Illegal Reentry"},
            {"I", "Too Many Skaters"},
            {"I", "Interference"},
            {"I", "Stalling"},
            {"N", "Insubordination"},
            {"Z", "Delay of Game"},
            {"G", "Misconduct"},
            {"G", "Gross Misconduct"}};
        String[][] sortedCodes = new String[penaltyCodes.length][2];
        for(int i = 0; i < sortedCodes.length; i++) {
            int min = -1;
            for(int j = 0; j < penaltyCodes.length; j++) {
                if(penaltyCodes[j] != null) {
                    if(min < 0) {
                        min = j;
                    } else if(penaltyCodes[j][1].compareTo(penaltyCodes[min][1]) < 0) {
                        min = j;
                    }
                }
            }
            sortedCodes[i] = penaltyCodes[min];
            penaltyCodes[min] = null;
        }
        
        PrintWriter out = new PrintWriter("sortedCodes.csv");
        for(int i = 0; i < sortedCodes.length; i++) {
            out.println(sortedCodes[i][1] +"," +sortedCodes[i][0]);
        }
        out.close();
    }
    
}
