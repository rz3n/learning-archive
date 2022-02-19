
/**
 * q2_LeapYears
 * Write a program that displays all the leap years, 10 per line,
 * from 101 to 2100, separated by exactly one space. Also display
 * the number of leap years in this period.
 * 
 * @author Ricardo Franzen 
 * @version 0.1
 */

import java.util.*;
public class q2_LeapYears {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        final int firstYear = 101;
        final int lastYear = 2100;
        int t = 0;
        
        System.out.printf("\nLeap years between %d and %d\n", firstYear, lastYear);
        
        for (int y = firstYear; y <= lastYear; y++) {
            if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
                if (t % 10 != 0) {
                    System.out.printf("%6d", y);
                    t++;
                } else {
                    System.out.printf("\n%6d", y);
                    t++;
                }
            }
        }
        
        System.out.printf("\nTotal of leap years: %d", t);
    }
}
