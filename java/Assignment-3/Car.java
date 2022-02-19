
/**
 * Specification of a Car class suited to keeping track of simple car rentals. This class is complete
 * and intended for use in the Fall 2021 CMPP269 Assignment #3 car rental application.
 *
 * @author Doug Shier
 * @version June 2021
 */
public class Car
{
    // static variables
    public static int rentalCount = 0;
    public static int rateSum = 0;
    
    // instance variables
    private String make;
    private String model;
    private int year;
    private int rate;
    private boolean needsService;
    private boolean isRented;
    private String renterName;
    private String renterPhone;

    /**
     * No-arg constructor for objects of class Car
     */
    public Car()
    {
        // initialise instance variables
        make = "";
        model = "";
        year = 0;
        rate = 0;
        needsService = false;
        isRented = false;
        renterName = "";
        renterPhone = "";
    }

    /**
     * Full-arg constructor for objects of class Car
     */
    public Car(String make, String model, int year, int rate, boolean needsService,
        boolean isRented, String renterName, String renterPhone)
        {
        // assign parameter values to instance variables
        this.make = make;
        this.model = model;
        this.year = year;
        this.rate = rate;
        this.needsService = needsService;
        this.isRented = isRented;
        this.renterName = renterName;
        this.renterPhone = renterPhone;
    }
    
    /**
     * setMake method - assigns parameter value to instance variable
     *
     * @param  make     new value to be stored in the respective instance variable
     */
    public void setMake(String make)
    {
        this.make = make;
    }
    
    /**
     * getMake method - returns current value of instance variable
     *
     * @return          current value of the respective instance variable
     */
    public String getMake()
    {
         return make;
    }
    
    /**
     * setModel method - assigns parameter value to instance variable
     *
     * @param  model     new value to be stored in the respective instance variable
     */
    public void setModel(String model)
    {
        this.model = model;
    }
    
    /**
     * getModel method - returns current value of instance variable
     *
     * @return           current value of the respective instance variable
     */
    public String getModel()
    {
         return model;
    }
    
    /**
     * setYear method - assigns parameter value to instance variable
     *
     * @param  year     new value to be stored in the respective instance variable
     */
    public void setYear(int year)
    {
        this.year = year;
    }
    
    /**
     * getYear method - returns current value of instance variable
     *
     * @return          current value of the respective instance variable
     */
    public int getYear()
    {
         return year;
    }
    
    /**
     * setRate method - assigns parameter value to instance variable
     *
     * @param  rate     new value to be stored in the respective instance variable
     */
    public void setRate(int rate)
    {
        this.rate = rate;
    }
    
    /**
     * getRate method - returns current value of instance variable
     *
     * @return          current value of the respective instance variable
     */
    public int getRate()
    {
         return rate;
    }
    
    /**
     * setNeedsService method - assigns parameter value to instance variable
     *
     * @param  needsService     new value to be stored in the respective instance variable
     */
    public void setNeedsService(boolean needsService)
    {
        this.needsService = needsService;
    }
    
    /**
     * getNeedsService method - returns current value of instance variable
     *
     * @return          current value of the respective instance variable
     */
    public boolean getNeedsService()
    {
         return needsService;
    }
    
    /**
     * setRented method - assigns values to instance variables to indicate that the car's
     *                    status is rented
     *
     * @param  renterName     customer name to be stored in the respective instance variable
     * @param  renterPhone    customer phone to be stored in the respective instance variable
     */
    public void setRented(String renterName, String renterPhone)
    {
        this.isRented = true;
        this.renterName = renterName;
        this.renterPhone = renterPhone;
        
        // increment counter to show that the car object has been rented and add its rate to the rate sum
        rentalCount++;
        rateSum += this.rate;
    }
    
    /**
     * setReturned method - resets instance variables to indicate that the car's status is
     *                      returned
     *
     */
    public void setReturned()
    {
        this.isRented = false;
        this.renterName = "";
        this.renterPhone = "";
    }
    
    /**
     * getIsRented method - returns current value of instance variable
     *
     * @return          current value of the respective instance variable
     */
    public boolean getIsRented()
    {
         return isRented;
    }
    
    /**
     * getRenterName method - returns current value of instance variable
     *
     * @return           current value of the respective instance variable
     */
    public String getRenterName()
    {
         return renterName;
    }
    
    /**
     * getRenterPhone method - returns current value of instance variable
     *
     * @return           current value of the respective instance variable
     */
    public String getRenterPhone()
    {
         return renterPhone;
    }
    
    /**
     * toString method - returns a readable representation of a car's state
     *
     * @return          a formatted String containing instance variable values
     */
    public String toString()
    {
        String result = String.format("%d %s %s Rate: $%d Status: %s%s", getYear(), getMake(), getModel(), getRate(),
            (getIsRented()) ? "Rented (" + getRenterName() + " " + getRenterPhone() + ")": "Not Rented",
            (getNeedsService()) ? ", Needs Service" : "");
        return result;
    }
}
