
/**
 * IceCreamCone - Lab Exam #4
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

class IceCreamCone {
    private int scoops;
    private double price;
    private String summary;
    private String type;
    
    final double scoop1 = 3.00;
    final double scoop2 = 4.25;
    final double scoop3 = 5.25;
    final double coneWaffle = 2.00;
    final double coneSugar = 1.50;
    
    public IceCreamCone(int scoops, String type) {
        this.scoops = scoops;
        this.type = type;
        
        if (this.scoops == 1)
            this.price = scoop1;
        else if (this.scoops == 2)
            this.price = scoop2;
        else if (this.scoops == 3)
            this.price = scoop3;

        if (this.type.equals("Waffle"))
            this.price += coneWaffle;
        else if (this.type.equals("Sugar"))
            this.price += coneSugar;
    }
    
    public double getConePrice() {    
        return this.price;
    }
    
    public String toString() {
        summary = this.scoops + " scoops in a " + this.type + " cone: $" + this.price;
        return summary;
    }
}
