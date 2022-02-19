
/**
 * Exercise12_19 - Write a program that counts the number of words in President
 * Abraham Lincoln’s Gettysburg address from 
 * https://liveexample.pearsoncmg.com/data/Lincoln.txt.
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;
import java.io.*;

public class Exercise12_19 {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        File textFile = new File("Lincoln.txt");
        int count = 0;
        String word;
        
        try {
            Scanner input = new Scanner(textFile);
            
            while (input.hasNext()) {
                word = input.next();
                if (!word.equals("--"))
                    count++;
            }
        } catch (FileNotFoundException ex) {}

        System.out.println("The file " + textFile + " has " + count + " words");
    }
}
