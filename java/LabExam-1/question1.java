
/**
 * question1 - Write a description here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class question1 {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        final Double priceGJ = 4.24;
        final Double admFee = 54.25;
        final Double charge = 1.05;
        
        Double previousReading;
        Double currentReading;
        Double gasUsed;
        Double totalCharge;
        
        System.out.printf("\nCurrent meter reading: ");
        currentReading = in.nextDouble();
        
        System.out.printf("\nPrevious meter reading: ");
        previousReading = in.nextDouble();
        
        gasUsed = ((currentReading - previousReading) * priceGJ);
        totalCharge = (gasUsed + admFee) * charge;
        
        System.out.printf("\nBill Details");
        System.out.printf("\nPrice for gas used: %.2f", gasUsed);
        System.out.printf("\nAdmin Fee: %.2f", admFee);
        System.out.printf("\nTotal charge: %.2f", totalCharge);
    }
}
