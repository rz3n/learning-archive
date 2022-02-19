
/**
 * question2 - Write a description here.
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;

public class question2 {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        // Constants
        final double rate1 = 151.0;
        final double rate2 = 159.0;
        final double rate3 = 146.0;

        // Variables
        int option = 0;
        int nights = 0;
        double reservationTotal;
        double dailyTotal = 0.0;
        
        while (option != 9) {
            System.out.printf("\nEnter your option (1-3), 9 to quit: ");
            option = in.nextInt();
            if(option != 9) {
                System.out.printf("\nHow many nights? ");
                nights = in.nextInt();
            }
            
            switch (option) {
                case 1:
                    reservationTotal = rate1 * nights;
                    dailyTotal += reservationTotal;
                    System.out.printf("\nReservation Summary");
                    System.out.printf("\nNights        %d", nights);
                    System.out.printf("\nRate     %.2f", rate1);
                    System.out.printf("\nTotal    %.2f", reservationTotal);
                break;
                case 2:
                    reservationTotal = rate2 * nights;
                    dailyTotal += reservationTotal;
                    System.out.printf("\nReservation Summary");
                    System.out.printf("\nNights        %d", nights);
                    System.out.printf("\nRate     %.2f", rate2);
                    System.out.printf("\nTotal    %.2f", reservationTotal);
                break;
                case 3:
                    reservationTotal = rate3 * nights;
                    dailyTotal += reservationTotal;
                    System.out.printf("\nReservation Summary");
                    System.out.printf("\nNights        %d", nights);
                    System.out.printf("\nRate     %.2f", rate3);
                    System.out.printf("\nTotal    %.2f", reservationTotal);
                break;
                case 9:
                break;
                default:
                    System.out.printf("\n! Invalid option !");
            }
        }
        
        System.out.printf("\nDaily Summary");
        System.out.printf("\n    Total    %.2f", dailyTotal);
    }
}
