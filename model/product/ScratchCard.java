public class ScratchCard extends NonExpirableProduct {

    /**
     * Constructor for ScratchCard product
     * 
     * @param name     Product name
     * @param price    Product price
     * @param quantity Available quantity
     */
    public ScratchCard(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    // ScratchCard doesn't implement Shippable as it's a digital product
    // that doesn't require physical shipping
}