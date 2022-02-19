/*
 * Assignment 2 - Employee Salary
 * Nov 13, 2021
 * 
 * Description: The program will prompt the user for a file path to a employee information file,
 *              the file path to a salary information file and the first and last name of an
 *              employee. The program will parse the employee information file for a matching name
 *              and use the associated employee ID to find their salary information in the salary
 *              information file. The employees salary information for twelve months will be
 *              calculated and printed to the console screen in a table.
 */

// import Scanner and File classes
import java.util.Scanner;
import java.io.File;

public class Assignment_2 {
    public static void main (String [] args) {
        //declare variables
        Scanner in = new Scanner (System.in);
        String employeeFileName, salaryFileName, lastName, firstName, employeeID;
        
        //Read file paths and user info
        System.out.print("Enter employee filename (full path): ");
        employeeFileName = in.nextLine();
        System.out.print("Enter employee salary filename (full path): ");
        salaryFileName = in.nextLine();
        System.out.print("Enter employee last name: ");
        lastName = in.nextLine();
        System.out.print("Enter employee first name: ");
        firstName = in.nextLine();
        
        //find employee ID
        employeeID = lookupEmployeeID(employeeFileName, firstName, lastName);
        
        //if employee ID is found print the resulting salary schedule, if not 
        if (!employeeID.equals(""))
            printEmployeeSalary(salaryFileName, employeeID, firstName, lastName);
        else
            System.out.printf("No Employee information found for: %s %s", firstName, lastName);
        
    }
    
    
    //Finds the first instance of an employeeID in a file given the file name, first and last names of the employee
    private static String lookupEmployeeID (String fileName, String firstName, String lastName){
        Scanner file;
        String employeeID = "";
        boolean found = false;

        try {
            //open file and set delimiter
            file = new Scanner(new File(fileName));
            file.useDelimiter(",|\r\n");
            
            //scan through file until name is found
            while (file.hasNextLine() && !found){
                //compare names in file to parameters and set employeeID if found
                if (file.next().equalsIgnoreCase(firstName) && file.next().equalsIgnoreCase(lastName)){
                    file.next();
                    employeeID = file.next();
                    found = true;
                } else
                    file.nextLine();
            }
            file.close();
        //handle file not found/IO exceptions
        } catch (Exception e) {
        }  

        return employeeID;
    }
    
    //Will find employee salary information in a file and print the information to the console in a table
    private static void printEmployeeSalary (String fileName, String employeeID, String firstName, String lastName){
        Scanner file;
        boolean found = false;
        double monthlySalary = 0.0, fedTax = 0.0, abTax = 0.0, cpp = 0.0, ei = 0.0;
        
        try {
            //open file and set delimiter
            file = new Scanner(new File(fileName));
            file.useDelimiter(",|\r\n");
            
            //discard the header
            file.nextLine();

            //scan through file until name is found
            while (file.hasNextLine() && !found){
                //compare names in file to parameters and set employeeID if found
                if (file.next().equals(employeeID)){
                    monthlySalary = file.nextDouble();
                    fedTax = file.nextDouble();
                    abTax = file.nextDouble();
                    cpp = file.nextDouble();
                    ei = file.nextDouble();
                    found = true;
                } else
                    file.nextLine();
            }
            file.close();

            System.out.printf("%nSalary schedule for %s %s (%s)", firstName.toUpperCase(), lastName.toUpperCase(), employeeID.toUpperCase());
            printPaySchedule(monthlySalary, fedTax, abTax, cpp, ei);
            
        //handle file not found/IO exceptions
        } catch (Exception e) {
        }
    }
    
    //Prints the rows of a table showing the employees pay schedule for 12 months
    private static void printPaySchedule (double grossSalary, double fedTax, double albertaTax, double cpp, double ei){
        final double cppMax = 3166.45;
        final double eiMax = 889.54;
        double monthlyTax = 0.0, netSalary = 0.0;
        double totalGrossSalary = 0.0, totalNetSalary = 0.0, totalTax = 0.0, totalCpp = 0.0, totalEi = 0.0;
        String reportLine = "-----------------------------------------------------------------";
        
        // print report
        System.out.printf("%n%17s%48s", "Gross", "Net");
        System.out.printf("%n%5s %11s %11s %11s %11s %11s", "Month", "Salary", "Tax", "CPP", "EI", "Salary");
        System.out.printf("%n%s", reportLine);
        for(int month=1; month <= 12; month++) {
            
            // sum salary
            totalGrossSalary += grossSalary;
            
            // sum taxes
            monthlyTax = fedTax + albertaTax;
            totalTax += monthlyTax;
            
            // sum cpp
            if((cppMax - totalCpp) > cpp) {
                totalCpp += cpp;
            } else if((cppMax - totalCpp) > 0) {
                cpp = cppMax - totalCpp;
                totalCpp += cpp;
            } else {
                cpp = 0;
            }
            
            // sum ei
            if((eiMax - totalEi) > ei) {
                totalEi += ei;
            } else if((eiMax - totalEi) > 0) {
                ei = eiMax - totalEi;
                totalEi += ei;
            } else {
                ei = 0;
            }


            // sum netSalary
            netSalary = grossSalary - monthlyTax - cpp - ei;
            totalNetSalary += netSalary;
            
            
            System.out.printf("%n%5d %,11.2f %,11.2f %,11.2f %,11.2f %,11.2f",
                month, grossSalary, monthlyTax, cpp, ei, netSalary) ;
        }
        System.out.printf("%n%s%n", reportLine);
        System.out.printf("Total %,11.2f %,11.2f %,11.2f %,11.2f %,11.2f%n", 
            totalGrossSalary, totalTax, totalCpp, totalEi, totalNetSalary);
    }
}