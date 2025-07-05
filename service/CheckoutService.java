import java.util.List;
import shipping.Shippable;

public class CheckoutService {
    private ShippingService shippingService;

    /**
     * Constructor with dependency injection
     * 
     * @param shippingService The shipping service to use
     */
    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    /**
     * Processes checkout for a customer with their cart
     * 
     * @param customer The customer making the purchase
     * @param cart     The shopping cart with items
     * @throws IllegalStateException if checkout cannot be processed
     */
    public void processCheckout(Customer customer, ShoppingCart cart) {
        // Validate preconditions
        validateCheckoutCart(cart);

        // Calculate amounts
        double subtotal = cart.calculateSubtotal();
        List<Shippable> shippableItems = cart.getShippableItems();
        double shippingFee = shippingService.calculateShippingCost(shippableItems);
        double totalAmount = subtotal + shippingFee;

        // Check customer balance
        if (!customer.hasSufficientBalance(totalAmount)) {
            throw new IllegalStateException("Insufficient customer balance");
        }

        // Process shipment if there are shippable items
        if (!shippableItems.isEmpty()) {
            shippingService.processShipment(shippableItems);
        }

        // Process payment and update inventory
        customer.deductBalance(totalAmount);
        cart.processCheckout();

        // Display checkout receipt
        displayCheckoutReceipt(cart, subtotal, shippingFee, totalAmount, customer.getBalance());

        // Clear cart after successful checkout
        cart.clear();
    }

    /**
     * Validates all preconditions for checkout cart
     * 
     * @param customer The customer
     * @param cart     The shopping cart
     * @throws IllegalStateException if any precondition fails
     */
    private void validateCheckoutCart(ShoppingCart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        cart.validateAvailability();
    }

    /**
     * Displays the checkout receipt to console
     * 
     * @param cart             The shopping cart
     * @param subtotal         The subtotal amount
     * @param shippingFee      The shipping fee
     * @param totalAmount      The total amount paid
     * @param remainingBalance The customer's remaining balance
     */
    private void displayCheckoutReceipt(ShoppingCart cart, double subtotal,
            double shippingFee, double totalAmount,
            double remainingBalance) {
        System.out.println("** Checkout receipt **");

        // Display each item with quantity and total price
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s %.0f%n",
                    item.getQuantity(),
                    item.getProduct().getName(),
                    item.getTotalPrice());
        }

        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shippingFee);
        System.out.printf("Amount %.0f%n", totalAmount);
        System.out.printf("Customer balance after payment: %.0f%n", remainingBalance);
    }
}