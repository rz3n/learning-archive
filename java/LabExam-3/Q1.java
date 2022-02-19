
/**
 * Q1 - Write a program to read in a list of electric vehicles.
 * Print out information on the least expensive car.
 * 
 * Data is stored in the following format:
 * <automobile name><newline>
 * <list price><space><range>
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;
import java.io.*;

public class Q1 {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        boolean found = false;
        String carModel, lowerCarModel = "";
        double carPrice, lowerCarPrice = 999999999.0;
        double carRange, lowerCarRange = 0.0;
        
        
        
        System.out.println("Enter the input filename: ");
        File textFile = new File(in.nextLine());

        try {
            Scanner file = new Scanner(textFile);
            file.useDelimiter(",|\r\n");
            
            while (file.hasNextLine() && !found) {
                carModel = file.next();
                String[] temp = file.next().split("\\s+");
                
                carPrice = Double.parseDouble(temp[0]);
                carRange = Double.parseDouble(temp[1]);
                
                if (lowerCarPrice > carPrice) {
                    lowerCarModel = carModel;
                    lowerCarPrice = carPrice;
                    lowerCarRange = carRange;
                }
            }
        } catch (Exception e) {}
        
        System.out.printf("\nLowest price EV is %s", lowerCarModel);
        System.out.printf("\nPrice $%.2f Range = %.0f", lowerCarPrice, lowerCarRange);
    }
}
