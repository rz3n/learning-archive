public class Rectanglex {
    double width = 1;
    double height = 1;
    
    // constructors
    Rectanglex() {
    }
    
    Rectanglex(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    // returns area
    double getArea() {
        return this.width * this.height;
    }
    
    // returns perimeter
    double getPerimeter() {
        return 2 * (this.width + this.height);
    }
}
