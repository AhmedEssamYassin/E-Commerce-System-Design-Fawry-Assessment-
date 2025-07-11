
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.customer.*;
import model.order.*;
import model.product.*;
import service.*;

// I strongly recommend reading the README file carefully first 
// to know all considerations and conventions in this project
public class Main {

    public static void main(String[] args) {
        // Initialize services
        ShippingService shippingService = new ShippingService();
        CheckoutService checkoutService = new CheckoutService(shippingService);

        // Create products
        Product cheese = new Cheese("Cheese", 100.0, 10, LocalDate.now().plusDays(5), 0.2);
        Product biscuits = new Biscuits("Biscuits", 150.0, 5, LocalDate.now().plusDays(3), 0.7);
        Product tv = new TV("TV", 500.0, 3, 15.0);
        Product mobile = new Mobile("Mobile", 800.0, 8, 0.3);
        Product scratchCard = new ScratchCard("Mobile Scratch Card", 50.0, 20);

        // Create customer with sufficient balance
        Customer customer = new Customer("Ahmed Yassin", 2000.0);

        System.out.println("\n=== E-Commerce System Demo ===\n");

        // Case 1: Successful checkout with mixed products
        System.out.println("Case 1: Successful checkout with mixed products");
        List<CartItem> list = new ArrayList<>();

        list.add(new CartItem(cheese, 2));
        list.add(new CartItem(biscuits, 1));
        list.add(new CartItem(tv, 1));
        list.add(new CartItem(mobile, 1));
        list.add(new CartItem(scratchCard, 2));
        demonstrateSuccessfulCheckout(checkoutService, customer, list);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Case 2: Error scenarios
        System.out.println("Case 2: Error scenarios\n");
        demonstrateErrorScenarios(checkoutService, customer, cheese, mobile);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Case 3: Product expiration scenario
        System.out.println("Case 3: Product expiration scenario");
        demonstrateExpirationScenario();
    }

    /**
     * Demonstrates a successful checkout with mixed shippable and non-shippable
     * products
     */
    private static void demonstrateSuccessfulCheckout(CheckoutService checkoutService,
            Customer customer,
            List<CartItem> list) {
        try {
            ShoppingCart cart = new ShoppingCart();

            // Add products to cart (following the example format from requirements)
            for (CartItem P : list) {
                cart.addProduct(P.getProduct(), P.getQuantity());
            }

            System.out.println("Customer balance before checkout: " + customer.getBalance());
            System.out.println("Cart contents:");
            for (CartItem item : cart.getItems()) {
                System.out.println("  " + item);
            }
            System.out.println();

            // Process checkout
            checkoutService.processCheckout(customer, cart);

        } catch (Exception e) {
            System.err.println("Error during checkout: " + e.getMessage());
        }
    }

    /**
     * Demonstrates various error scenarios
     */
    private static void demonstrateErrorScenarios(CheckoutService checkoutService,
            Customer customer,
            Product cheese,
            Product mobile) {

        // Scenario 1: Empty cart
        System.out.println("Scenario 1: Empty cart error");
        try {
            ShoppingCart emptyCart = new ShoppingCart();
            checkoutService.processCheckout(customer, emptyCart);
        } catch (IllegalStateException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        System.out.println();

        // Scenario 2: Insufficient quantity
        System.out.println("Scenario 2: Insufficient quantity error");
        try {
            ShoppingCart cart = new ShoppingCart();
            cart.addProduct(cheese, 50); // Requesting more than available (only 8 left after previous purchase)
        } catch (IllegalArgumentException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        System.out.println();

        // Scenario 3: Insufficient customer balance
        System.out.println("Scenario 3: Insufficient customer balance error");
        try {
            Customer poorCustomer = new Customer("Poor Customer", 10.0);
            ShoppingCart cart = new ShoppingCart();
            cart.addProduct(mobile, 1);
            checkoutService.processCheckout(poorCustomer, cart);
        } catch (IllegalStateException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        System.out.println();

    }

    /**
     * Demonstrates expired product scenario
     */
    private static void demonstrateExpirationScenario() {
        System.out.println("Creating expired product...");
        try {
            // Create expired cheese (expired yesterday)
            Product expiredCheese = new Cheese("Expired Cheese", 50.0, 5,
                    LocalDate.now().minusDays(1), 0.3);

            ShoppingCart cart = new ShoppingCart();
            cart.addProduct(expiredCheese, 1);

        } catch (IllegalArgumentException e) {
            System.out.println("Expected error when adding expired product: " + e.getMessage());
        }

        System.out.println();

    }
}
