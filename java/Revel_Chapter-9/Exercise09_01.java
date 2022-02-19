
/**
 * Exercise09_01 - Write a description here.
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;

public class Exercise09_01 {
    public static void main (String [] args) {
        Rectangle rectangle;

        rectangle = new Rectangle(4.0, 40.0);
        System.out.print("\nThe area of a rectangle with width 4.0 and height 40.0 is "
            + rectangle.getArea());
        System.out.print("\nThe perimeter of a rectangle is " + rectangle.getPerimeter());

        rectangle = new Rectangle(3.5, 35.9);
        System.out.print("\nThe area of a rectangle with width 3.5 and height 35.9 is "
            + rectangle.getArea());
        System.out.print("\nThe perimeter of a rectangle is " + rectangle.getPerimeter());
    }
}

class Rectangle {
    private double width = 1;
    private double height = 1;

    // constructors
    public Rectangle() {}

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // returns area
    public double getArea() {
        return this.width * this.height;
    }

    // returns perimeter
    double getPerimeter() {
        return 2 * (this.width + this.height);
    }
}