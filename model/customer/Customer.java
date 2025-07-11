package model.customer;
public class Customer {
    private String name;
    private double balance;

    /**
     * Constructor for customer
     * 
     * @param name    Customer name
     * @param balance Initial balance (must be non-negative)
     */
    public Customer(String name, double balance) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.name = name;
        this.balance = balance;
    }

    /**
     * Checks if customer has sufficient balance for a purchase
     * 
     * @param amount The amount to check against balance
     * @return true if customer has sufficient balance
     */
    public boolean hasSufficientBalance(double amount) {
        return balance >= amount;
    }

    /**
     * Deducts the specified amount from customer's balance
     * 
     * @param amount The amount to deduct
     * @throws IllegalArgumentException if amount is greater than balance
     */
    public void deductBalance(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("Customer: %s (Balance: %.2f)", name, balance);
    }
}