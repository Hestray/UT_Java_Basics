package isp.lab5.exercise1;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    // attributes
    private ArrayList<Product> products = new ArrayList<>();        // the size of this should be maximum 10
    private String orderId;
    private LocalDateTime date;
    private Double totalPrice = 0d;
    private Customer customer;

    // methods
    public Order(String orderId, Product product, Customer customer) {
        this.orderId    = orderId;
        this.date       = LocalDateTime.now();
        this.customer   = customer;
        this.products.add(product);
        calculatePrice();
    }
    public Order(String orderId, ArrayList<Product> products, Customer customer) {
        this.orderId    = orderId;
        this.date       = LocalDateTime.now();
        this.customer   = customer;
        this.products   = products;
        calculatePrice();
    }

    public void calculatePrice() {
        double sum = 0d;
        for (Product product : products) {
            sum += product.getPrice();
        }
        setTotalPrice(sum);
    }

    @Override
    public String toString() {
        return "Order ID: " + this.orderId + "\tCustomer: " + this.customer.getName() + "\tTotal price is: " + this.totalPrice;
    }

    // setters and getters
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setOrderId(String orderId) {
        this.orderId = this.products.size() + this.customer.getCustomerId() + this.date;
    }

    public void setProducts(ArrayList<Product> products) {
        int i = 0;
        if (products.size() > 10)
            throw new ArithmeticException("You must have a maximum of 10 products in your shopping cart.");
        else
            this.products = products;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getOrderId() {
        return orderId;
    }
}
