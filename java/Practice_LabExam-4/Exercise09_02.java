
/**
 * Exercise09_02 - Write a description here.
 * 
 * @author Ricardo Franzen
 * @version 0.1
 */

import java.util.*;

public class Exercise09_02 {
    public static void main (String [] args) {
        Stock stock;
        
        stock = new Stock("ORCL", "Oracle Corporation", 34.5, 34.35);
        stock.getChangePercent();
    }
}

class Stock {
    String symbol;
    String name;
    double previousClosingPrice;
    double currentPrice;
    
    public Stock(String symbol, String name, double previousClosingPrice, double currentPrice) {
        this.symbol = symbol;
        this.name = name;
        this.previousClosingPrice = previousClosingPrice;
        this.currentPrice = currentPrice;
    }
    
    public void getChangePercent() {
        double priceChange = 0.0;
        
        priceChange = ((this.currentPrice - this.previousClosingPrice) % this.previousClosingPrice) * 100;
    
        System.out.printf("\nPrevious Closing Price: %.2f", this.previousClosingPrice);
        System.out.printf("\nCurrent Price: %.2f", this.currentPrice);
        System.out.printf("\nPrice Change: %.2f%%", priceChange);
    }
}