/**
 * rockPaperScissors - let's play
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;
public class rockPaperScissors {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);

        // variables
        boolean control;
        int user, program;
        int result=2;
        String[] rps = {"Rock", "Paper", "Scissors"};
        String[] winner = {"You Win", "You loose", "Its a draw"};
        String repeat;
        Random rand = new Random();

        System.out.println("\n> Let's play...");

        do {
            do {
                System.out.println("\n> Choose your move");
                System.out.println("0 - Rock \n1 - Paper \n2 - Scissors");
                user = in.nextInt();
                
                if ((user < 0) || (user > 2)) {
                    control = false;
                    System.out.println("\nInvalid option, try again.\n");
                } else {
                    control = true;
                }
            } while (control == false);

            program = rand.nextInt(3);

            System.out.println("> Program choosed: " + rps[program]);
            if(user == 0) {
                if(program == 0) {
                    result=2;
                }
                if(program == 1) {
                    result=1;
                }
                if(program == 2) {
                    result=0;
                }
            }
            if(user == 1) {
                if(program == 0) {
                    result=0;
                }
                if(program == 1) {
                    result=2;
                }
                if(program == 2) {
                    result=1;
                }
            }
            if(user == 2) {
                if(program == 0) {
                    result=1;
                }
                if(program == 1) {
                    result=0;
                }
                if(program == 2) {
                    result=2;
                }
            }

            // print the result
            System.out.printf("\nResult: %s", winner[result]);

            //repeat?
            System.out.printf("\n\nPlay it again? (y/n)");
            repeat = in.next();
        } while (repeat.equals("y"));
    }
}
