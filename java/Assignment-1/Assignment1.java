/*
 * Assignment1 - Import Exemptions
 * Oct 10, 2021
 */

/*
 * This program will calculate the amount of personal tax exemptions for a traveller 
 * returning to Canada based on length of absences and value of goods being imported. 
 * It will prompt the user to enter three inputs: Name, Length of absence and value of 
 * goods being imported.  The program will then calculate the usable personal exemption 
 * amounts as well as the amounts subject to duty and taxes.  The results will then be 
 * displayed in a table format.
 */

import java.util.*;

public class Assignment1 {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        
        // variables declaration
        double absence, amountImported;
        double maximumPersonalExemption = 0;
        double usablePersonalExemption  = 0;
        double amountSubjectSpecialDuty = 0;
        double amountSubjectRegularDuty = 0;
        String limitedItems             = "No";
        String lastName, firstName, initialsName;
        String maxExemptString, usableExemptString, specialDutyString, regularDutyString, totalString;
        
        // system's name
        System.out.println("> Import Exeption system\n");
        
        // collecting data
        System.out.print("Travelers's Name (Last Name,  First Name,  Initials): ");
        lastName = in.next();
        firstName = in.next();
        initialsName = in.next();
        
        // remove comma from names
        lastName = lastName.replace(",", "");
        firstName = firstName.replace(",", "");
        
        System.out.print("Length of absence from Canada (# of days): ");
        absence = in.nextDouble();
        
        System.out.print("Total amount of imported goods ($ CAD): ");
        amountImported = in.nextDouble();
        
        if(absence < 1) {
            // less than one day
            
            // Total amount must be under regular duty
            amountSubjectRegularDuty = amountImported;
        } else if (absence < 2) {
            // between one and two days
            
            // total value of goods allowed
            maximumPersonalExemption = 200;
            
            if (amountImported > 200) {
                // Total amount must be under regular duty
                amountSubjectRegularDuty = amountImported;
            } else {
                // under the limit
                usablePersonalExemption = amountImported;
            }
        } else if (absence >= 2) {
            // more than two days
            
            // Limited amount of alcoholic beverage and tobacco
                limitedItems = "Yes";
                // total value of goods allowed
                maximumPersonalExemption = 800;
            if (amountImported > 800) {
                if (amountImported <= 1100) {
                    usablePersonalExemption = 800;
                    amountSubjectSpecialDuty = amountImported - 800;
                } else {
                    usablePersonalExemption = 800;
                    amountSubjectSpecialDuty = 300;
                    amountSubjectRegularDuty = amountImported - 1100;
                }
            } else {
                // under the limit
                usablePersonalExemption = amountImported;
            }
        }

        maxExemptString = "$" + maximumPersonalExemption + (int)((maximumPersonalExemption * 100) % 10);
        usableExemptString = "$" + usablePersonalExemption + (int)((usablePersonalExemption * 100) % 10);
        specialDutyString = "$" + amountSubjectSpecialDuty + (int)((amountSubjectSpecialDuty * 100) % 10);
        regularDutyString = "$" + amountSubjectRegularDuty + (int)((amountSubjectRegularDuty * 100) % 10);
        totalString = "$" + amountImported + (int)((amountImported * 100) % 10);

       // print the report
        System.out.printf("%nImport Exemption Report for %s %s %s", firstName, initialsName, lastName);
        System.out.printf("%n------------------------------------------------------------");
        System.out.printf("%nAbsence Period (days) %28.1f", absence);
        System.out.printf("%n...Maximum Personal Exemption  %19s", maxExemptString);
        System.out.printf("%n...Include Limited Alcohol & Tobacco? %12s", limitedItems);
        System.out.printf("%nUsable Personal Exemption %34s", usableExemptString);
        System.out.printf("%nAmount Subject to Special Duty & Taxes %21s", specialDutyString);
        System.out.printf("%nAmount Subject to Regular Duty & Taxes %21s", regularDutyString);
        System.out.printf("%n                                                  ----------");
        System.out.printf("%nTotal Amount of Imported Goods %29s", totalString);
        System.out.printf("%n------------------------------------------------------------%n");
    }
}
