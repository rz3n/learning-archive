
/**
 * question2 - Write a description here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class question2 {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);

        final Double annualIncomeRate1 = 0.15;
        final Double annualIncomeConstant1 = 0.0;
        final Double annualIncomeLimit1 = 45282d;
        final Double annualIncomeRate2 = 0.205;
        final Double annualIncomeConstant2 = 2491.0;
        final Double annualIncomeLimit2 = 90563d;
        final Double annualIncomeRate3 = 0.260;
        final Double annualIncomeConstant3 = 7471.0;
        
        Double monthlySalary;
        Double annualSalary;
        Double annualFederalTax;
        Double monthlyAFederalTax;
        Double summary;
        
        System.out.printf("\nEnter your monthly pay: ");
        monthlySalary = in.nextDouble();
        
        annualSalary = monthlySalary * 12;
        
        if (annualSalary <= annualIncomeLimit1) {
            annualFederalTax = (annualIncomeRate1 * annualSalary) - annualIncomeConstant1;
        } else if (annualSalary <= annualIncomeLimit2) {
            annualFederalTax = (annualIncomeRate2 * annualSalary) - annualIncomeConstant2;
        } else {
            annualFederalTax = (annualIncomeRate3 * annualSalary) - annualIncomeConstant3;
        }
        
        monthlyAFederalTax = annualFederalTax / 12;
        summary = monthlySalary - monthlyAFederalTax;
        
        System.out.printf("\n           Desc     Amount");
        System.out.printf("\n         Salary %10.2f", monthlySalary);
        System.out.printf("\nMonthly fed tax %10.2f", monthlyAFederalTax);
        System.out.printf("\n        Summary %10.2f", summary);
    }
}
