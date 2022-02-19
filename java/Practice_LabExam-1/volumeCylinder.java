
/**
 * volumeCylinder - This class calculates the volume of a cylinder
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;
public class volumeCylinder {
    public static void main (String [] args) {
        // Create a Scanner object attached to the keyboard
        Scanner in = new Scanner (System.in);

        // variables
        double volume = 0;
        double radius = 0;
        double height = 0;
        boolean control;

        // title
        System.out.printf("\n> Volume calculator - Cylinder");

        // repeats untill user inform positive numbers
        do {
            // reads radius
            System.out.printf("\nInform the RADIUS of the cylinder: ");
            radius = in.nextDouble();
            
            // reads height
            System.out.printf("\nInform the HEIGHT of the cylinder: ");
            height = in.nextDouble();
            
            if ((radius > 0) && (height > 0)) {
                control = true;
            } else {
                control = false;
                System.out.println("\n!! Numbers must be positive !!\n");
            }
        } while (control == false);

        // calculate
        volume = Math.PI * radius * radius * height;

        // print result
        System.out.printf("\nThe volume of this Cylinder is: %.2f", volume);

    }
}
