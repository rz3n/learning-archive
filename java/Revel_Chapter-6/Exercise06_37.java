
/**
 * Exercise06_37 - Write a description here.
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;

public class Exercise06_37 {
    public static String format(int number, int width) {
        String formatted = String.format("%0"+ width + "d" , number);
        return formatted;
    }
    
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        System.out.printf("\nEnter an Integer: ");
        int number = in.nextInt();
        
        System.out.printf("\nEnter the width: ");
        int width = in.nextInt();
        
        System.out.printf("The formatted number is %s", format(number, width));
    }
}
