
/**
 * Exercise09_08 - Write a description here.
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;

public class Exercise09_08 {
    public static void main (String [] args) {
        Fan fan;

        fan = new Fan(3, true, 10, "yellow");
        System.out.println(fan.toString());
        
        fan.setSpeed(2);
        fan.setColor("blue");
        fan.setRadius(5.0);
        fan.setOn(false);
        
        System.out.println(fan.toString());
    }
}

class Fan {
    final int SLOW = 1;
    final int MEDIUM = 2;
    final int FAST = 3;
    int speed;
    boolean on;
    double radius;
    String color;

    public Fan() {
        this.speed = this.SLOW;
        this.on = false;
        this.radius = 5;
        this.color = "blue";
    }
    
    public Fan(int speed, boolean on, double radius, String color) {
        this.speed = speed;
        this.on = on;
        this.radius = radius;
        this.color = color;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public String getSpeed() {
        return "speed " + this.speed;
    }
    
    public void setOn(boolean on) {
        this.on = on;
    }
    
    public String getOn() {
        if (this.on)
            return "fan is on";
        else
            return "fan is off";
    }
    
    public void setRadius(double radius) {
        this.radius = radius;
    }
    
    public String getRadius() {
        return "radius " + this.radius;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public String getColor() {
        return "color " + this.color;
    }
    
    public String toString() {
        String description = "speed " + this.speed;
        description += "\ncolor " + this.color;
        description += "\nradius " + this.radius;
        
        return description;
    }
}