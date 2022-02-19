
/**
 * q1 - Phone Key Pads
 * Write a program that prompts the user to enter a
 * lowercase or uppercase letter and displays its 
 * corresponding number. For a nonletter input, display
 * invalid input.
 * 
 * @Ricardo Franzen 
 * @0.1
 */
// Standard import for the Scanner class
import java.util.*;

public class q1_phoneKeyPads {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        // numbers and letters
        String b2 = "abc";
        String b3 = "def";
        String b4 = "ghi";
        String b5 = "jkl";
        String b6 = "mno";
        String b7 = "pqrs";
        String b8 = "tuv";
        String b9 = "wxyz";
        
        System.out.printf("Enter a lowercase or uppercase letter: ");
        String letter = in.next();
        
        if ((letter.charAt(0) >= 'A' && letter.charAt(0) <= 'z')) {
            
            if (b2.indexOf(letter.toLowerCase()) != -1) {
                System.out.printf("Corresponding number: 2");
            } else if (b3.indexOf(letter.toLowerCase()) != -1) {
                System.out.printf("Corresponding number: 3");
            } else if (b4.indexOf(letter.toLowerCase()) != -1) {
                System.out.printf("Corresponding number: 4");
            } else if (b5.indexOf(letter.toLowerCase()) != -1) {
                System.out.printf("Corresponding number: 5");
            } else if (b6.indexOf(letter.toLowerCase()) != -1) {
                System.out.printf("Corresponding number: 6");
            } else if (b7.indexOf(letter.toLowerCase()) != -1) {
                System.out.printf("Corresponding number: 7");
            } else if (b8.indexOf(letter.toLowerCase()) != -1) {
                System.out.printf("Corresponding number: 8");
            } else if (b9.indexOf(letter.toLowerCase()) != -1) {
                System.out.printf("Corresponding number: 9");
            }
            
            
        } else {
            System.out.printf("Invalid input. Ending program...");
        }
    }
}
