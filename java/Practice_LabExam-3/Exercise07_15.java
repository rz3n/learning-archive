
/**
 * Exercise07_15 - Write a description here.
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;

public class Exercise07_15 {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        int [] intList = new int[10];
        int size = 0;
        
        System.out.println("Enter ten integers: ");
        for(int x = 0; x < intList.length; x++) {
            intList[x] = in.nextInt();
        }
        
        intList = eliminateDuplicates(intList);

        for(int x = 0; x < intList.length; x++)
            if(intList[x] != 0)
                size++;
        
        System.out.print("\nThe number of distinct integer is " + size);
        System.out.print("\nThe distinct integers are ");
        for(int x = 0; x < intList.length; x++)
            if(intList[x] != 0)
                System.out.print(intList[x] + " ");
    }
    
    public static int[] eliminateDuplicates(int[] list) {
        int [] temp = new int[10];
        int position = 0;
        boolean found = false;
        
        for(int x = 0; x < list.length; x++) {
            for(int y = 0; y < list.length; y++) {
                if(list[x] == temp[y])
                    found = true;
            }
            if (found == false) {
                temp[position] = list[x];
                position++;
            }
            
            found = false;
        }

        list = temp;
        return list;
    }
}
