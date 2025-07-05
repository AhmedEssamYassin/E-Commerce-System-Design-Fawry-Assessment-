public class CartItem {
    private Product product;
    private int quantity;

    /**
     * Constructor for cart item
     * 
     * @param product  The product being added to cart
     * @param quantity The quantity of the product
     */
    public CartItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Calculates the total price for this cart item
     * 
     * @return The total price (product price * quantity)
     */
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    /**
     * Checks if the requested quantity is available in stock
     * 
     * @return true if product is available for the requested quantity
     */
    public boolean isAvailable() {
        return product.isAvailable(quantity);
    }

    /**
     * Reduces the product quantity when checkout is successful
     */
    public void processCheckout() {
        product.reduceQuantity(quantity);
    }

    // Getters
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.format("%dx %s", quantity, product.getName());
    }
}