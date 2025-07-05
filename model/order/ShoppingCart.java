import java.util.ArrayList;
import java.util.List;
import shipping.Shippable;

public class ShoppingCart {
    private List<CartItem> items;

    /**
     * Constructor initializes empty cart
     */
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds a product to the cart with specified quantity
     * 
     * @param product  The product to add
     * @param quantity The quantity to add
     * @throws IllegalArgumentException if product is not available or quantity is
     *                                  invalid
     */
    public void addProduct(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (!product.isAvailable(quantity)) {
            throw new IllegalArgumentException(
                    String.format("Product \"%s\" is not available for quantity %d",
                            product.getName(), quantity));
        }
        if (product.isExpired()) {
            throw new IllegalArgumentException(
                    String.format("Product \"%s\" is Expired and not safe to use",
                            product.getName(), quantity));
        }
        // Check if product already exists in cart, if so update quantity
        // This is enough for now as items in a cart cannot be too large in a way that
        // affect performance
        // If the cart scaled up later on we may use better data structure to find and
        // update such as HashMap
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                int newQuantity = item.getQuantity() + quantity;
                if (!product.isAvailable(newQuantity)) {
                    throw new IllegalArgumentException(
                            String.format("Total quantity %d exceeds available stock for %s",
                                    newQuantity, product.getName()));
                }
                // Remove old item and add new one with updated quantity
                items.remove(item);
                items.add(new CartItem(product, newQuantity));
                return;
            }
        }

        // Product not in cart, add new item
        items.add(new CartItem(product, quantity));
    }

    /**
     * Calculates the subtotal of all items in the cart
     * 
     * @return The subtotal amount
     */
    public double calculateSubtotal() {
        return items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    /**
     * Checks if the cart is empty
     * 
     * @return true if cart has no items
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Validates all items in the cart are still available
     * 
     * @throws IllegalStateException if any item is not available
     */
    public void validateAvailability() {
        for (CartItem item : items) {
            if (!item.isAvailable()) {
                throw new IllegalStateException(
                        String.format("Product \"%s\" is no longer available",
                                item.getProduct().getName()));
            }
            if (item.getProduct().isExpired()) {
                throw new IllegalArgumentException(
                        String.format("Product \"%s\" is Expired and not safe to use",
                                item.getProduct().getName(), item.getProduct().getQuantity()));
            }
        }
    }

    /**
     * Processes checkout by reducing product quantities
     */
    public void processCheckout() {
        for (CartItem item : items) {
            item.processCheckout();
        }
    }

    /**
     * Gets all shippable items from the cart
     * 
     * @return List of shippable items
     */
    public List<Shippable> getShippableItems() {
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct() instanceof Shippable) {
                // Add each unit of the product separately for shipping
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add((Shippable) item.getProduct());
                }
            }
        }
        return shippableItems;
    }

    /**
     * Gets all cart items
     * 
     * @return List of cart items
     */
    public List<CartItem> getItems() {
        return new ArrayList<>(items); // Return defensive copy, so others cannot change this instance.
    }

    /**
     * Clears all items from the cart
     */
    public void clear() {
        items.clear();
    }
}