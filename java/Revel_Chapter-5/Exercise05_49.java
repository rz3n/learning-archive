/*
 * (Count vowels and consonents)
 * Assume letters A, E, I, O, and U as the vowels.
 * Write a program that prompts the user to enter a string and 
 * displays the number of vowels and consonants in the string.
 *
 * Sample Run
 * Enter a string: Programming is fun
 * The number of vowels is 5
 * The number of consonants is 11
 */

import java.util.*;
public class Exercise05_49 {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);

        System.out.println("Enter a string: ");
        String phrase = in.nextLine();
        phrase = phrase.toLowerCase();

        int vowel = 0;
        int consonant = 0;

        for (int x=0; x < phrase.length(); x++) {
            if (phrase.charAt(x) == 'a' || 
            phrase.charAt(x) == 'e' || 
            phrase.charAt(x) == 'i' ||
            phrase.charAt(x) == 'o' ||
            phrase.charAt(x) == 'u') {
                vowel++;
            } else if (phrase.charAt(x) >= 'a' && phrase.charAt(x) <= 'z') {
                consonant++;
            }
        }

        System.out.println("The number of vowels is " + vowel);
        System.out.println("The number of consonants is " + consonant);
    }
}
