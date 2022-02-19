
/**
 * Exercise11_13
 * (Remove duplicates)
 * Write a method that removes the duplicate elements from an array list 
 * of integers using the following header:
 * public static void removeDuplicate(ArrayList<Integer> list)
 *
 * Write a test program that prompts the user to enter 10 integers to a 
 * list and displays the distinct integers in their input order separated 
 * by exactly one space.
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;
//import java.io.*;

public class Exercise11_13 {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        ArrayList<Integer> numbers = new ArrayList<>();
        
        System.out.println("Enter ten integers: ");
        for(int x = 0; x < 10; x++) {
            numbers.add(in.nextInt());
        }
        
        removeDuplicate(numbers);
        System.out.print("\nThe distinct integers are ");
        for(int x = 0; x < numbers.size(); x++) {
            System.out.print(numbers.get(x) + " ");
        }
    }
    
    public static void removeDuplicate(ArrayList<Integer> list) {
        
        ArrayList<Integer> newList = new ArrayList<>();
        
        for(int x = 0; x < list.size(); x++) {
            if(!newList.contains(list.get(x)))
                newList.add(list.get(x));
        }
        
        list.clear();
        
        for (int x = 0; x < newList.size(); x++) {
            list.add(newList.get(x));
        }
    }
}
