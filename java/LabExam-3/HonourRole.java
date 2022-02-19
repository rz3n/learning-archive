
/**
 * Q2 - Write a description here.
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;

public class HonourRole {

    public static void main (String [] args) {
        // DO NOT change any code in the main method!!!

        String [] studentNames = { "Sara", "Karen", "Ahmed", "Riley", "Mohammed"};
        double [] g = { 65.2, 98.4, 89.9, 90.2, 75.4};

        int count;

        System.out.printf ("%n%10s %15s  %-15s%n", "Name", "Grade", "Designation");
        count = printHonoursStudents (studentNames, g);

        System.out.printf ("%n%d students received honours%n", count);

    }

    // Write the method printHonoursStudents here
    public static int printHonoursStudents(String[] names, double[] grades) {
        int honours = 0;
        String designation;
        double presidentAward = 95.0;
        double honoursAward = 90.0;
        
        for(int x = 0; x < names.length; x++) {
            if(grades[x] >= presidentAward) {
                designation = "President's Award";
                honours++;
            } else if(grades[x] >= honoursAward) {
                designation = "Honours";
                honours++;
            } else
                designation = "";
            
            System.out.printf("%10s %15.2f%%  %-15s%n", names[x], grades[x], designation);
        }

        return honours;
    }
}