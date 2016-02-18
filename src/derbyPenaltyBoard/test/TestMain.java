/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard.test;

import java.awt.Color;
import java.io.File;

/**
 *
 * @author nbp184
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file = new File("test");
        file = new File(file, ".txt");
        System.out.println(file.getAbsolutePath());
    }
    
}
