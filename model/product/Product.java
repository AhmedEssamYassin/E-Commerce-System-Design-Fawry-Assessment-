package model.product;
public abstract class Product {
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructor to initialize product with basic information
     * 
     * @param name     The product name
     * @param price    The product price (must be positive)
     * @param quantity The available quantity (must be non-negative)
     */
    public Product(String name, double price, int quantity) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Template method to check if product is available for purchase
     * Combines quantity check with expiration check
     * 
     * @param requestedQuantity The quantity customer wants to purchase
     * @return true if product is available, false otherwise
     */
    public boolean isAvailable(int requestedQuantity) {
        return hasEnoughQuantity(requestedQuantity);
    }

    /**
     * Checks if there's enough quantity available
     * 
     * @param requestedQuantity The quantity to check
     * @return true if enough quantity is available
     */
    private boolean hasEnoughQuantity(int requestedQuantity) {
        return this.quantity >= requestedQuantity;
    }

    /**
     * Reduces the product quantity after successful purchase
     * 
     * @param soldQuantity The quantity that was sold
     */
    public void reduceQuantity(int soldQuantity) {
        if (soldQuantity > this.quantity) {
            throw new IllegalArgumentException("Cannot reduce quantity below zero");
        }
        this.quantity -= soldQuantity;
    }

    /**
     * Abstract method to be implemented by subclasses
     * Follows Open/Closed Principle - open for extension, closed for modification
     * 
     * @return true if product is expired, false otherwise
     */
    public abstract boolean isExpired();

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.format("%s (Price: %.2f, Quantity: %d)", name, price, quantity);
    }
}