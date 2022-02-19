import java.util.*;
public class Exercise04_13 {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        System.out.println("Enter a letter: ");
        String letter = in.next();
        
        if (letter.equalsIgnoreCase("a") || 
        letter.equalsIgnoreCase("e") || 
        letter.equalsIgnoreCase("i") ||
        letter.equalsIgnoreCase("o") ||
        letter.equalsIgnoreCase("u")) {
            System.out.printf("%s is a vowel", letter);
        } else {
            System.out.printf("%s is a consonant", letter);
        }
    }
}
