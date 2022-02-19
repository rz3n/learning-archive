/*
 * Assignment 3 - Classic Car Rentals
 * Dec 12, 2021
 * 
 * Description: This program will prompt the user for a data file with cars information. The program will load this 
 * file to the memory and prompt some options for the user. The options are:
 *  - Rent a car, Return a car, Flag a car that needs servicing, Clear a car from servicing and Exit.
 * 
 * When user choose the exit option, this program will write all data back to the file and print how many rentals 
 * were made during this session and the total rent rate revenue.
 */

// import required classes
import java.util.*;
import java.io.*;

public class CarRentals {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        Car car, currCar;

        // vars
        String carDataFile;
        ArrayList<Car> classicCars = new ArrayList<>();
        String menuOption = "";
        String renterName, renterPhone;
        int carChoice;

        // welcome message
        System.out.print("*** Welcome to Mo's Classic Car Rentals ***\n");

        // read file path
        System.out.print("Enter car data filename: ");
        carDataFile = in.nextLine();

        // loads the content from file to classicCars ArrayList
        loadFile(carDataFile, classicCars);

        while(!menuOption.equals("e")) {
            System.out.println("\n                    *    *    *");

            // get a list of all cars and status
            printCars(classicCars);

            // read user option
            menuOption = getOption(in);

            switch(menuOption.toLowerCase()) {
                case "a": // rent a car
                    // user input
                    System.out.print("Rent a car. Enter car selection (by number): ");
                    carChoice = in.nextInt();
                    in.nextLine();
                    
                    // gets car info
                    currCar = classicCars.get(carChoice - 1);
                    
                    // if car is not rented and dont needs service get renters data
                    if (!currCar.getIsRented() && !currCar.getNeedsService()){
                        System.out.print("Enter renter's name: ");
                        renterName = in.nextLine();
                        System.out.print("Enter renter's phone #: ");
                        renterPhone = in.nextLine();

                        currCar.setRented(renterName, renterPhone);
                    } else {
                        System.out.println("The " + currCar.getMake() + " " + currCar.getModel() + " is not available to rent.");
                        System.out.print("Press [Enter] to continue...");
                        in.nextLine();
                    }
                    break;

                case "b": // return a car
                    // user input
                    System.out.print("Return a car. Enter car selection (by number): ");
                    carChoice = in.nextInt();
                    in.nextLine();
                    
                    // gets car info
                    currCar = classicCars.get(carChoice - 1);
                    
                    // if car is rented set to returned
                    if (currCar.getIsRented())
                        classicCars.get(carChoice - 1).setReturned();
                    else {
                        System.out.println("The " + currCar.getMake() + " " + currCar.getModel() + " is not rented and cannot be returned.");
                        System.out.print("Press [Enter] to continue...");
                        in.nextLine();
                    }
                    break;

                case "c": // flag for servicing
                    // user input
                    System.out.print("Flag car for servicing. Enter car selection (by number): ");
                    carChoice = in.nextInt();
                    in.nextLine();
                    
                    // gets car info
                    classicCars.get(carChoice - 1).setNeedsService(true);
                    break;

                case "d": // clear car from servicing
                    // user input
                    System.out.print("Clear car from servicing. Enter car selection (by number): ");
                    carChoice = in.nextInt();
                    in.nextLine();
                    
                    // gets car info
                    currCar = classicCars.get(carChoice - 1);
                    
                    // if car needs service, change to dont needs
                    if (currCar.getNeedsService())
                        classicCars.get(carChoice - 1).setNeedsService(false);
                    else {
                        System.out.println("The " + currCar.getMake() + " " + currCar.getModel() + " is not flagged as needing service.");
                        System.out.print("Press [Enter] to continue...");
                        in.nextLine();
                    }
                    break;

                case "e": // exit && end
                    System.out.print("\nNumber of rentals initiated in this session: " + Car.rentalCount);
                    System.out.print("\nTotal rental rate revenue from this session: $" + Car.rateSum);
                    System.out.print("\n\nGood bye!");
                    
                    // write to data file
                    saveCars(carDataFile, classicCars);
                    break;
            }
        }
    }

    // generate options menu
    private static String getOption(Scanner in) {
        System.out.print("\n\nOptions");
        System.out.print("\n A. Rent a car");
        System.out.print("\n B. Return a car");
        System.out.print("\n C. Flag car for servicing");
        System.out.print("\n D. Clear car from servicing");
        System.out.print("\n E. To exit program");
        System.out.print("\n\nEnter your option (by letter): ");
        return in.nextLine();
    }

    // prints all cars with information
    private static void printCars(ArrayList<Car> classicCars) {
        Car car;
        int i = 1;

        System.out.print("Cars");
        for (Car c: classicCars) {
            System.out.print("\n  " + i + ". " + c);
            i++;
        }
    }

    // opens the specified data file
    private static void loadFile(String fileName, ArrayList<Car> classicCars) {
        Scanner dataFile;
        Car car;
        String[] fileLine;
        String renterName, renterPhone, make, model;
        int year, rate;
        boolean service, rented;

        try {
            dataFile = new Scanner(new File(fileName));

            while (dataFile.hasNextLine()) {
                fileLine = dataFile.nextLine().split(",");

                year = Integer.parseInt(fileLine[0]);
                make = fileLine[1];
                model = fileLine[2];
                rate = Integer.parseInt(fileLine[3]);
                service = Boolean.parseBoolean(fileLine[4]);
                rented = Boolean.parseBoolean(fileLine[5]);
                if (rented)
                    renterName = fileLine[6];
                else
                    renterName = "";
                if (rented)
                    renterPhone = fileLine[7];
                else
                    renterPhone = "";

                car = new Car(make, model , year, rate, service, rented, renterName, renterPhone);
                classicCars.add(car);
            }
        } catch (Exception e) {}
    }

    // write current data to specified data file
    private static void saveCars(String fileName, ArrayList<Car> carList) {
        FileWriter file;
        try{    
            file = new FileWriter(fileName);
            for (Car car: carList){
                file.write(car.getYear() + "," + car.getMake() + "," + car.getModel() + "," + car.getRate() + ",");
                if (car.getNeedsService())
                    file.write("true,");
                else
                    file.write("false,");
                if (car.getIsRented())
                    file.write("true," + car.getRenterName() + "," + car.getRenterPhone() + "\n");
                else
                    file.write("false,,\n");
            }
            file.flush();
            file.close();
        }  catch (Exception e) {}   
    }
}

