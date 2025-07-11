package model.product;
import model.shipping.Shippable;

public class TV extends NonExpirableProduct implements Shippable {
    private double weight; // Weight in kilograms

    /**
     * Constructor for TV product
     * 
     * @param name     Product name
     * @param price    Product price
     * @param quantity Available quantity
     * @param weight   Weight in kilograms
     */
    public TV(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weight = weight;
    }

    /**
     * Implementation of Shippable interface
     * 
     * @return The weight of the TV in kilograms
     */
    @Override
    public double getWeight() {
        return weight;
    }
}