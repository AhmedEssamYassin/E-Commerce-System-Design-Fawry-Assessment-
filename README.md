# E-Commerce System – Fawry Quantum Internship Challenge

This is a Java-based e-commerce system designed for the Fawry Rise Journey Internship Challenge. The project demonstrates object-oriented design principles, adheres strictly to SOLID principles, and uses JavaDoc conventions for documentation.

## Project Overview

The goal of this project is to build a simple but extensible e-commerce platform with:

- A variety of products, some of which may expire or require shipping  
- Shopping cart with quantity and stock control  
- Checkout process handling subtotal, shipping costs, and balance deduction  
- Shipping integration for shippable products only  

## Design Phase

The system was designed using **UML modeling** and follows **SOLID principles**:

- **S**ingle Responsibility: Clear separation of concerns between product types, cart, customer, and services.  
- **O**pen/Closed: Product extensions (e.g. `Expirable`, `Shippable`) via interfaces and decorators.  
- **L**iskov Substitution: All product subtypes can be treated as a `Product`.  
- **I**nterface Segregation: `Shippable` and `Expirable` are small and purposeful.  
- **D**ependency Inversion: High-level modules (`CheckoutService`) depend on abstractions (`Shippable`), not concrete classes.  

### UML Diagram

A complete [UML Diagram](docs/UML%20Diagram.png).

## Code Phase

The application is organized into the following packages:

```
├── Main.java
├── model/
│   ├── customer/
│   │   └── Customer.java
│   ├── order/
│   │   ├── CartItem.java
│   │   └── ShoppingCart.java
│   ├── product/
│   │   ├── Biscuits.java
│   │   ├── Cheese.java
│   │   ├── ExpirableProduct.java
│   │   ├── Mobile.java
│   │   ├── NonExpirableProduct.java
│   │   ├── Product.java
│   │   ├── ScratchCard.java
│   │   └── TV.java
│   └── shipping/
│       └── Shippable.java
├── service/
│   ├── CheckoutService.java
│   └── ShippingService.java

```

## JavaDoc Convention

All classes and public methods are documented using the official [Oracle JavaDoc style](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html).  

Each class includes:

- A class-level description  
- JavaDoc for each method, including:  
  - `@param` for input parameters  
  - `@return` for return values  
  - `@throws` where exceptions may be thrown  

Example:

```java
/**
 * Represents a customer with a name and account balance.
 * Handles operations related to payment and balance tracking.
 */
public class Customer {

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
}
```

## Features

- Add products to cart (with stock check)  
- Handle expirable and shippable items  
- Validate checkout constraints (empty cart, insufficient balance, expired item, out-of-stock)  
- Display full checkout receipt and shipping summary  

## Build & Run

```bash
$files = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -d bin $files
cd bin
java Main
```

## Author

Fawry Rise Journey Challenge – Intern Submission  
Designed and Developed by Ahmed Essam Yassin

---