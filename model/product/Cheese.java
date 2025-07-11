package model.product;
import java.time.LocalDate;
import model.shipping.Shippable;

public class Cheese extends ExpirableProduct implements Shippable {
    private double weight; // Weight in kilograms

    /**
     * Constructor for Cheese product
     * 
     * @param name           Product name
     * @param price          Product price
     * @param quantity       Available quantity
     * @param expirationDate Expiration date
     * @param weight         Weight in kilograms
     */
    public Cheese(String name, double price, int quantity, LocalDate expirationDate, double weight) {
        super(name, price, quantity, expirationDate);
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weight = weight;
    }

    /**
     * Implementation of Shippable interface
     * 
     * @return The weight of the cheese in kilograms
     */
    @Override
    public double getWeight() {
        return weight;
    }
}