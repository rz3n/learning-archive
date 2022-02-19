/*
 * Assignment 3 - Classic Car Rentals
 * Dec 12, 2021
 * 
 * Description: 
 */

// import required classes
import java.util.*;
import java.io.*;

public class CarRentalsSelf {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        Car car;
        
        // vars
        String carDataFile;
        String menuOption = "";
        ArrayList<Car> classicCars = new ArrayList<>();
        int totalRentals = 0;
        double totalRevenue = 0;
        
        // welcome message
        System.out.print("*** Welcome to Mo's Classic Car Rentals ***\n");
        
        // read file path
        System.out.print("Enter car data filename: ");
        carDataFile = in.nextLine();
        //carDataFile = "classiccars.csv"; //temporary for lazy issues :)
        
        // loads the content from file to classicCars ArrayList
        loadFile(carDataFile, classicCars);
        
        
        while(true) {
            int carSelect = 0;
            String renterName = "";
            String renterPhone = "";
            
            System.out.println("\n                              *    *    *\n");
        
            // get the list of all cars and status
            printCars(classicCars);

            // print available options
            printMenuOptions();
        
            // read user option
            System.out.print("\n\nEnter your option (by letter): ");
            menuOption = in.nextLine();
        
            switch(menuOption.toLowerCase()) {
                case "a": // rent a car
                    System.out.print("Rent a car. Enter car selection (by number): ");
                    carSelect = in.nextInt();
                    carSelect--;
                    
                    // get car info
                    car = classicCars.get(carSelect);
                    
                    //if car dont needs service
                    if (!car.getNeedsService()) {
                        //if car is not rented
                        if (!car.getIsRented()) {
                            in.nextLine(); // some bug. without this the program is jumping renterName reading
                            System.out.println("Enter the renter's name: ");
                            renterName = in.nextLine();
                            System.out.println("Enter the renter's phone #: ");
                            renterPhone = in.nextLine();

                            car.setRented(renterName, renterPhone);

                            totalRentals++;
                            totalRevenue += car.getRate();
                        } else
                            printNotAvailable(car.getMake(), car.getModel());
                    } else
                        printNotAvailable(car.getMake(), car.getModel());
                    break;
                    
                case "b": // return a car
                    System.out.print("Return a car. Enter car selection (by number): ");
                    carSelect = in.nextInt();
                    carSelect--;
                    
                    // get car info
                    car = classicCars.get(carSelect);
                    if (car.getIsRented()) {
                        car.setReturned();
                    } else {
                        printNotRented(car.getMake(), car.getModel());
                    }
                    break;
                
                case "c": // flag for servicing
                    System.out.print("Flag car for servicing. Enter car selection (by number): ");
                    carSelect = in.nextInt();
                    carSelect--;
                    
                    // get car info
                    car = classicCars.get(carSelect);
                    car.setNeedsService(true);
                    break;
                    
                case "d": // clear car from servicing
                    System.out.print("Clear car from servicing. Enter car selection (by number): ");
                    carSelect = in.nextInt();
                    carSelect--;
                    
                    // get car info
                    car = classicCars.get(carSelect);
                    
                    if (car.getNeedsService()) {
                        car.setNeedsService(false);
                    } else {
                        printDontNeedService(car.getMake(), car.getModel());
                    }
                    break;
                    
                case "e": // exit
                    System.out.printf("\nNumber of rentals initiated in this session: %d", totalRentals);
                    System.out.printf("\nTotal rental rate revenue from this session: $%.0f", totalRevenue);
                    System.out.printf("\n\nGood bye!");
                    
                    // write content from ArrayList classicCars to file
                    writeFile(carDataFile, classicCars);
                    
                    System.exit(0);
                    break;
            }
            
            System.out.print("\nPress [Enter] to continue...");
            in.nextLine();
        }
    }

    private static void printMenuOptions() {
        System.out.print("\n\nOptions");
        System.out.print("\n A. Rent a car");
        System.out.print("\n B. Return a car");
        System.out.print("\n C. Flag car for servicing");
        System.out.print("\n D. Clear car from servicing");
        System.out.print("\n E. To exit program");
    }
    
    private static void printCars(ArrayList<Car> classicCars) {
        Car car;
        int i = 1;
        
        System.out.print("Cars");
        for (Car c: classicCars) {
            System.out.print("\n  " + i + ". " + c);
            i++;
        }
    }
    
    private static void printNotAvailable(String make, String model) {
        System.out.print("\nThe " + make + " " + model + " is not available to rent." );
    }
    
    private static void printNotRented(String make, String model) {
        System.out.print("\nThe " + make + " " + model + " is not rented and cannot be returned." );
    }
    
    private static void printDontNeedService(String make, String model) {
        System.out.print("\nThe " + make + " " + model + " is not flagged as needing service." );
    }
    
    private static void loadFile(String fileName, ArrayList<Car> classicCars) {
        Car car;
        
        try {
            Scanner dataFile = new Scanner(new File(fileName));
            
            String renterName;
            String renterPhone;
            
            while (dataFile.hasNextLine()) {
                String[] fileLine = dataFile.nextLine().split(",");
                
                int year = Integer.parseInt(fileLine[0]);
                String make = fileLine[1];
                String model = fileLine[2];
                int rate = Integer.parseInt(fileLine[3]);
                boolean service = Boolean.parseBoolean(fileLine[4]);
                boolean rented = Boolean.parseBoolean(fileLine[5]);
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
            
            dataFile.close();
        } catch (Exception e) {}
    }
    
    private static void writeFile(String fileName, ArrayList<Car> classicCars) {
        Car car;

        try {
            FileWriter dataFile = new FileWriter(fileName);
            
            // empty file
            dataFile.write("");
            
            for (int i = 0; i < classicCars.size(); i++) {
                String carInfo = "";
                
                car = classicCars.get(i);
                
                carInfo = car.getYear() + ",";
                carInfo += car.getMake() + ",";
                carInfo += car.getModel() + ",";
                carInfo += car.getRate() + ",";
                carInfo += car.getNeedsService() + ",";
                carInfo += car.getIsRented() + ",";
                if (car.getIsRented()) {
                    carInfo += car.getRenterName() + ",";
                    carInfo += car.getRenterPhone();
                }
                else {
                    carInfo += ",";
                }
                carInfo += System.lineSeparator();
                
                dataFile.write(carInfo);
            }
            
            dataFile.close();
        } catch (Exception e) {}
        
    }
}
