package isp.lab5.exercise1;

import java.util.List;

public class Product {
    // attributes
    private String productId;
    private String name;
    private double price;
    private ProductCategory productCategory;

    // methods
    public Product(String productId, String name, double price) {
        this.productId  = productId;
        this.name       = name;
        this.price      = price;
    }
    public Product(String productId, String name, double price, ProductCategory type) {
        this.productId  = productId;
        this.name       = name;
        this.price      = price;
        this.productCategory = type;
    }

    @Override
    public String toString() {
        return this.productId + "\t\tProduct: " + this.name + "\t\tFrom category: " + this.productCategory + "\t\tPrice: " + this.price;
    }

    // setters & getters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public String getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }
}
