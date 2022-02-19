import java.util.*;
import java.io.*;

public class Exercise12_13 {
    public static void main (String[] args) {
        File localFile = new File(args[0]);

        int lines = 0;
        int words = 0;
        int chars = 0;

        try {
            Scanner fileContent = new Scanner(localFile);
      
            while (fileContent.hasNextLine()) {
                String line = fileContent.nextLine();
                lines++;
                chars += line.length();
            }
        } catch (FileNotFoundException ex) {}
        
        try {
            Scanner fileContent = new Scanner(localFile);
            
            while (fileContent.hasNext()) {
                fileContent.next();
                words++;
            }
        } catch (FileNotFoundException ex) {}
        
        System.out.printf("File %s has", args[0]);
        System.out.printf("\n%d characters", chars);
        System.out.printf("\n%d words", words);
        System.out.printf("\n%d lines\n", lines);
    }
}