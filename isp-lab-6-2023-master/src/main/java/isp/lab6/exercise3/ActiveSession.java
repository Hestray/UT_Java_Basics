package isp.lab6.exercise3;

import java.util.HashMap;
import java.util.Map;

public class ActiveSession {
    private String username;
    private Map<Product, Double> shoppingCart = new HashMap<>(); // product, quantity; product by quantity in the cart

    public ActiveSession(String username) {
        // start with an empty cart
        this.username = username;
    }

    public ActiveSession(String username, Map<Product, Double> shoppingCart) {
        // start with a non-empty cart
        this.username       = username;
        this.shoppingCart   = shoppingCart;
    }
    /**
     *
     * @param product
     * @param quantity
     * @return the status which indicates what action was performed. 0 means that product was not added to the cart,
     * 1 means that the quantity of the product within the cart was modified since the product was already in the cart,
     * 2 means that the product was added to the cart.
     */
    public int addToCart(Product product, double quantity) {
        if (quantity != 0) {
            for (Map.Entry<Product, Double> item : shoppingCart.entrySet()) {
                if (item.getKey().equals(product)) {
                    // if product is already found within the cart, just change the quantity to the new one
                    item.setValue(quantity);
                    return 1;
                }
            }
            // if product is not found, add the product
            shoppingCart.put(product, quantity);
            return 2;
        }
        else return 0;
    }

    public String getUsername() {
        return username;
    }

    public Map<Product, Double> getShoppingCart() {
        return shoppingCart;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setShoppingCart(Map<Product, Double> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
