public class Circle {
    // The radius of this circle
    double radius = 1;
    
    // construct a circle object
    Circle() {
    }
    
    Circle(double newRadius) {
        radius = newRadius;
    }
    
    // return the area of this circle
    double getArea() {
        return radius * radius * Math.PI;
    }
    
    // return the perimeter of this circle
    double getPerimeter() {
        return 2 * radius * Math.PI;
    }
    
    // set a new radius for this circle
    void setRadios(double newRadius) {
        radius = newRadius;
    }
}
