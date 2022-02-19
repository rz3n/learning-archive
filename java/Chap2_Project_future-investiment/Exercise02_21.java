import java.util.*;
import java.lang.Math;

public class Exercise02_21 {
    public static void main (String [] args) {
        double investmentAmount, monthlyInterestrate, annualInterestRate, futureInvestmentValue;
        int numberOfYears;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter investiment amount: ");
        investmentAmount = input.nextDouble();
        
        System.out.println("Enter annual interest rate in percentage: ");
        annualInterestRate = input.nextDouble();
        
        annualInterestRate *= 0.01;
        monthlyInterestrate = annualInterestRate / 12;
        
        System.out.println("Enter number of years: ");
        numberOfYears = input.nextInt();
        
        futureInvestmentValue = investmentAmount * Math.pow(1 + monthlyInterestrate, numberOfYears * 12);
        System.out.printf("Future value is: $%.2f", futureInvestmentValue);
    }
}