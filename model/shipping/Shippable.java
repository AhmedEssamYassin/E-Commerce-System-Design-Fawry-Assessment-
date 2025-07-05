package shipping;

public interface Shippable {
    /**
     * Gets the name of the shippable item
     * 
     * @return The name of the item
     */
    String getName();

    /**
     * Gets the weight of the shippable item in kilograms
     * 
     * @return The weight in kg
     */
    double getWeight();
}