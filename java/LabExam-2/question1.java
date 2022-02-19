
/**
 * question1 - Write a description here.
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;

public class question1 {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        System.out.printf("\nEnter the winning numbers separated by ':': ");
        String lotteryNumbers = in.next();
        
        String number;
        int matches = 0;
        
        do {
            System.out.printf("\nEnter you ticket numbers one at a time (0 to quit): ");
            number = in.next();
            
            if (lotteryNumbers.indexOf(number) != -1 && !Objects.equals(number, "0")) {
                matches++;
            }

        } while (!Objects.equals(number, "0"));

        System.out.printf("%d numbers match.", matches);
    }
}
