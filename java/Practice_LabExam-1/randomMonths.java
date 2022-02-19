/**
 * randomMonths - show a random month of the year
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;
public class randomMonths {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        // variables
        double x;
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        
        Random rand = new Random();
        int totalMonths = months.length;
        int randomMonth = rand.nextInt(totalMonths);
        
        // title
        System.out.printf("\n> Prints a random month of the year");
        
        // print result
        System.out.printf("\nThe random month is: %s", months[randomMonth+1]);
    }
}
