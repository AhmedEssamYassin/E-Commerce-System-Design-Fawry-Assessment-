package service;
import java.util.List;
import model.shipping.Shippable;

public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 2.0; // $2 per kg (Assumption, may not be correct)

    /**
     * Calculates shipping cost based on total weight of shippable items
     * 
     * @param shippableItems List of items that need to be shipped
     * @return The shipping cost
     */
    public double calculateShippingCost(List<Shippable> shippableItems) {
        double totalWeight = calculateTotalWeight(shippableItems);
        return totalWeight * SHIPPING_RATE_PER_KG;
    }

    /**
     * Processes shipment and displays shipment notice
     * 
     * @param shippableItems List of items to be shipped
     */
    public void processShipment(List<Shippable> shippableItems) {
        if (shippableItems.isEmpty()) {
            return; // No items to ship
        }

        System.out.println("** Shipment notice **");

        // Display each item with its weight
        for (Shippable item : shippableItems) {
            double weightInKg = item.getWeight();

            if (weightInKg < 1) {
                double weightInGrams = weightInKg * 1000.0;// Convert kg to grams
                System.out.printf("1x %s %.2fg%n", item.getName(), weightInGrams);
            } else {
                System.out.printf("1x %s %.0fkg%n", item.getName(), weightInKg);
            }
        }

        // Display total package weight
        double totalWeight = calculateTotalWeight(shippableItems);
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }

    /**
     * Calculates the total weight of all shippable items
     * 
     * @param shippableItems List of shippable items
     * @return Total weight in kilograms
     */
    private double calculateTotalWeight(List<Shippable> shippableItems) {
        return shippableItems.stream()
                .mapToDouble(Shippable::getWeight)
                .sum();
    }
}