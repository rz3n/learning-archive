
/**
 * q1_studentMajor
 * Write a program that prompts the user to enter two characters
 * and displays the major and status represented in the characters.
 * The first character indicates the major and the second is a 
 * number character 1, 2, 3, or 4, which indicates whether a student 
 * is a freshman, sophomore, junior, or senior. Suppose that the 
 * following characters are used to denote the majors:
 * M: Mathematics
 * C: Computer Science
 * I: Information Technology
 * 
 * @author: Ricardo Franzen
 * @version: 0.1
 */

import java.util.*;
public class q1_studentMajor {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);

        System.out.printf("Enter two characters: ");
        String student = in.next().toLowerCase();
        
        char major = student.charAt(0);
        char level = student.charAt(1);
        String majorOut = "";
        String levelOut = "";
        
        
        if (major == 'm') {
            majorOut = "Mathematics";
        } else if (major == 'c') {
            majorOut = "Computer Science";
        } else if (major == 'i') {
            majorOut = "Information Technology";
        }
        
        if (level == '1') {
            levelOut = "Freshman";
        } else if (level == '2') {
            levelOut = "Sophomore";
        } else if (level == '3') {
            levelOut = "Junior";
        } else if (level == '4') {
            levelOut = "Senior";
        }
        
        System.out.printf("%s %s", majorOut, levelOut);
    }
}
