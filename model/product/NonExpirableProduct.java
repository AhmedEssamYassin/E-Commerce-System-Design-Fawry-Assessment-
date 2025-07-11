package model.product;
public abstract class NonExpirableProduct extends Product {

    /**
     * Constructor for non-expirable products
     * 
     * @param name     Product name
     * @param price    Product price
     * @param quantity Available quantity
     */
    public NonExpirableProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    /**
     * Non-expirable products never expire
     * 
     * @return false always, as these products don't have expiration dates
     */
    @Override
    public boolean isExpired() {
        return false; // Non-expirable products never expire
    }
}