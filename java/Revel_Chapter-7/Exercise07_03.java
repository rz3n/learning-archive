/**
 * Exercise07_03
 * Write a program that reads the integers between 1 and 100 and
 * counts the occurrences of each.
 * Assume the input ends with 0.
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;

public class Exercise07_03 {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        int [] values = new int[100];
        int last = 0;
        int x = 0;
        int input = 0;
        String times;
        
        System.out.println("Enter the integers between 1 and 100: ");
        
        while ((input = in.nextInt()) != 0) {    
            if(input != 0)
                values[x] = input;

            x++;
        }
        
        Arrays.sort(values);
        
        for(x=0; x < values.length; x++) {
            if(last != values[x]) {
                int total = searchArray(values, values[x]);
                last = values[x];
                
                if(total == 1)
                    times = " time";
                else
                    times = " times";
                
                System.out.println(values[x] + " occurs " + total + times);
            }
        }
    }

    
    public static int searchArray(int [] x, int y) {
        int cont = 0;
        for(int i=0; i < x.length; i++) {
            if(x[i] == y)
                cont++;
        }
        return cont;
    }
}
