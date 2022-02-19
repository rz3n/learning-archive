
/**
 * Exercise7_8 - Write a test program that prompts the user to enter 10 integers, 
 * invokes the first method, then displays the average value; prompts the user to 
 * enter 10 double values, invokes the second method, then displays the average value.
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;

public class Exercise07_08 {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        int [] intList = new int[10];
        double [] doubleList = new double[10];
        
        System.out.println("Enter 10 integers: ");
        for(int x = 0; x < intList.length; x++) {
            intList[x] = in.nextInt();
        }
        
        System.out.println("The average for integers is " + average(intList));
        
        
        System.out.println("Enter 10 doubles: ");
        for(int x = 0; x < doubleList.length; x++) {
            doubleList[x] = in.nextDouble();
        }
        
        System.out.println("The average for double values is " + average(doubleList));
    }
    
    public static double average(int[] array) {
        double total = 0.0;
        for(int x = 0; x < array.length; x++) {
            total += array[x];
        }

        return (total / array.length);
    }
    
    public static double average(double[] array) {
        double total = 0.0;
        for(int x = 0; x < array.length; x++) {
            total += array[x];
        }

        return (total / array.length);
    }
}
