
/**
 * q2_HighestScore
 * Write a program that prompts the user to enter the number of 
 * students and each student’s name and score, and finally 
 * displays the name of the student with the highest score. 
 * Use the next() method in the Scanner class to read a name, 
 * rather than using the nextLine() method. Assume that the 
 * number of students is at least 1.
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;

public class q2_HighestScore {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);

        System.out.printf("How many students you will add? ");
        int numberStudents = in.nextInt();
        String studentName = "";
        int studentScore = 0;
        String hightName = "";
        int hightScore = 0;
        
        for (int t = 0; t < numberStudents; t++) {
            System.out.printf("\nType the student name and score: ");
            studentName = in.next();
            studentScore = in.nextInt();
            
            if (studentScore > hightScore) {
                hightScore = studentScore;
                hightName = studentName;
            }
        }
        
        System.out.printf("The student with the highest score is %s, and the score is %d", hightName, hightScore);
    }
}
