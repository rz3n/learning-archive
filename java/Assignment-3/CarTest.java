
/**
 * CarTest - A simple program to test/demonstrate Car class objects & methods using a small ArrayList
 *
 * @author Doug Shier
 * @version June 2021
 */
import java.util.*;
public class CarTest
{
    public static void main (String [] args) {
        ArrayList<Car> myCars = new ArrayList<>();
        Car car;

        // Create first car object, print it, and add it to the list
        // Order of arguments: make, model, year, rate, needsService, isRented, renterName, renterPhone
        car = new Car("Volkswagen", "Beetle" , 1966, 180, false, false, "", "");
        myCars.add(car);
        
        // Create second car object, print it, and add it to the list
        car = new Car("Chevrolet", "Camaro RS", 1970, 225, false, true, "AJ Walker", "587-333-3333");
        myCars.add(car);
        
        // Print initial car list
        System.out.println("Car list created. Here is the initial state:");
        for (Car c: myCars)
        {
            System.out.println("  " + c);
        }
        
        // Rent the first car
        car = myCars.get(0);
        if (!car.getIsRented())
        {
            car.setRented("Joy Ryder", "403-999-9999");
        }
        
        // Flag the second car for servicing 
        car = myCars.get(1);
        car.setNeedsService(true);

        // And then return it
        if (car.getIsRented())
        {
            car.setReturned();
        }
                
        // Print updated car list
        System.out.println("\nCar list updated. Here is the final state:");
        for (Car c: myCars)
        {
            System.out.println("  " + c);
        }
        
        System.out.printf("End of test. Number of new rentals: %d, Revenue: $%d%n",
            Car.rentalCount, Car.rateSum);
    }
}
