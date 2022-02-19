/**
 * poundToKg - This class converts pounds to kg
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;
public class poundToKg {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        // variables
        double lb, weight, kg;
        
        // static
        lb = 0.45359237;
        
        // title
        System.out.printf("\n> Weight converter - lb to kg");
        
        // reads the weight
        System.out.printf("\nInform the weight in pounds: ");
        weight = in.nextDouble();
        
        // calculate
        kg = weight * lb;
        
        // print result
        System.out.printf("\nThe weight in Kg is : %.3f", kg);
    }
}
