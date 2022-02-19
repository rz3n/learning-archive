
/**
 * Exercise07_09 - Write a test program that prompts the user to enter 10 numbers, 
 * invokes this method to return the minimum value, and displays the minimum value.
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;

public class Exercise07_09 {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        double [] doubleList = new double[10];
        
        System.out.println("Enter 10 double numbers: ");
        for(int x = 0; x < doubleList.length; x++) {
            doubleList[x] = in.nextDouble();
        }
        
        System.out.println("The minimal value is " + min(doubleList));
    }
    
    public static double min(double[] array) {
        double minimal = array[0];
        for(int x = 0; x < array.length; x++) {
            if(array[x] <= minimal)
                minimal = array[x];
        }

        return minimal;
    }
}
