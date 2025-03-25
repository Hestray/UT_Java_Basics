package isp.lab6.exercise3;

import java.lang.reflect.Array;
import java.util.*;

public class OnlineStore {
    private List<Product> products              = new ArrayList<>();
    private Map<String, ActiveSession> sessions = new HashMap<>();

    // sessions is a map of username attached to ActiveSession
    public OnlineStore(List<Product> products) {
        this.products.addAll(products);
    }
    public OnlineStore() {
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Its use shines when dealing with products read from a file or something similar to that.
     * @param products to be introduced in the shop
     */
    public void addProduct(ArrayList<Product> products) {
        this.products.addAll(products);
    }

    /**
     * Adds a session for each user. When starting a new session, the cart will be empty.
     * @param username user to start the session for
     */
    void addSession(String username) {
        // start a new session with an empty cart
        sessions.put(username, new ActiveSession(username));
    }

    /**
     * Deletes an existing session for a given user.
     * @param username user to delete the session for
     */
    void removeSession(String username) {
        sessions.remove(username);
    }

    /**
     * Sort the products based on a given criteria. The criteria must be a Comparator object and will be passed as
     * Class::attribute.
     * @param sortCriteria is the criteria to sort the list by.
     * @return the sorted list of products available in the shop.
     */
    public List<Product> getProductsSorted(Comparator<Product> sortCriteria) {
        ArrayList<Product> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(sortCriteria);
        return sortedProducts;
    }
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Adds a product to a car in a given quantity.
     * @param username whose cart the product will be added to.
     * @param product to add to the cart.
     * @param quantity desired to buy of the given product
     */
    public void addToCart(String username, Product product, double quantity) {
        int state = sessions.get(username).addToCart(product, quantity);
        if (state == 0) {
            System.out.println("Invalid quantity.");
        } else if (state == 1) {
            System.out.println("Changed quantity in the cart.");
        } else if (state == 2) {
            System.out.println("Added product to the cart.");
        }
    }

    /**
     * This method accesses the active session's cart, checks what products it has and calculates the total cost of the
     * products that can be found in the cart.
     * @param username is the user whose total is calculated.
     * @return the cost of all the products found in the cart
     */
    public String checkout(String username) {
        double total                  = 0; // represents the cost of all the products
        ActiveSession currentSession  = sessions.get(username); // unnecessary; here for coherence
        for (Map.Entry<Product, Double> entry : currentSession.getShoppingCart().entrySet()) {
            // for each product that can be found in the current user's (active session) cart
            total = total + entry.getValue() * entry.getKey().getPrice();
         // total = total +     quantity     *         price;
        }
        return "Your total is " + total;
    }

    public Map<String, ActiveSession> getSessions() {
        return sessions;
    }
}
