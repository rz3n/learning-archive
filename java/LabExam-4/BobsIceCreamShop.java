import java.util.*;

public class BobsIceCreamShop {

    public static void main (String [] args) {

        IceCreamCone cone1 = new IceCreamCone (2, "Regular");

        IceCreamCone cone2 = new IceCreamCone (3, "Sugar");

        IceCreamCone cone3 = new IceCreamCone (1, "Waffle");

        System.out.println ("Ice Cream Cone Order");

        System.out.println ();

        System.out.println (cone1);

        System.out.println (cone2);

        System.out.println (cone3);

        System.out.println ();

        System.out.printf ("Total: $%.2f", cone1.getConePrice() + cone2.getConePrice() + cone3.getConePrice());

    }

}