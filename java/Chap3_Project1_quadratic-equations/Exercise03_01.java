import java.util.*;
import java.lang.Math;

public class Exercise03_01 {
    public static void main (String [] args) {
        Scanner input = new Scanner(System.in);
        
        double a, b, c, discriminant, r1, r2;
        
        System.out.println("\nEnter a, b, c: ");
        a = input.nextDouble();
        b = input.nextDouble();
        c = input.nextDouble();

        // calc the discriminant
        discriminant = b * b - 4 * a * c;
        
        // title
        System.out.println("\n> Quadratic Equations");
        
        // check discrimintant
        if (discriminant > 0 ) {
          // calculate the two roots
          r1 = (-b + Math.sqrt(discriminant)) / (2 * a);
          r2 = (-b - Math.sqrt(discriminant)) / (2 * a);
          
          System.out.printf("\nThe equation has two roots Root 1: %f\nRoot 2: %f", r1, r2);
        } else if (discriminant == 0) {
            // calculate one root
            r1 = (-b / (2 * a));
            
            System.out.printf("\nThe equation has one root Root1: %f: ", r1);
        } else {
          System.out.printf("\nThe equation has no real roots");
        }
    }
}