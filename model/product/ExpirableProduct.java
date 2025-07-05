import java.time.LocalDate;

public abstract class ExpirableProduct extends Product {
    private LocalDate expirationDate;

    /**
     * Constructor for Expirable products
     * 
     * @param name           Product name
     * @param price          Product price
     * @param quantity       Available quantity
     * @param expirationDate The date when product expires
     */
    public ExpirableProduct(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
    }

    /**
     * Checks if the product has expired by comparing with current date
     * 
     * @return true if product is expired, false otherwise
     */
    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}