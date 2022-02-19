/**
 * sortInteger - sorts 3 integers informed by the user
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;
public class sortInteger {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        // variables
        int limit = 3;
        int[] numbers = new int[limit];
    
        // title
        System.out.printf("\n> Sorts ");
       
        // reads the values
        for(int x=0; x<limit; x++) {
            numbers[x] = in.nextInt();
        }
        
        // sort the array
        Arrays.sort(numbers);
        
        // print result
        System.out.printf("\nSorting your numbers: %s", Arrays.toString(numbers));
    }
}
