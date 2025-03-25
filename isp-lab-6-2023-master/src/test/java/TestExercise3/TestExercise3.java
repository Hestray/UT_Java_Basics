package TestExercise3;

import isp.lab6.exercise3.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestExercise3 {
    OnlineStore shop;
    User        user;

    @Before public void instantiateOnlineShop() {
        // create a list of products
        Product prod1 = new Product("Product 1", 13.5D);
        Product prod2 = new Product("Product 3", 97D);
        Product prod3 = new Product("Product 2", 45.25D);

        List<Product> products = new ArrayList<>();
        products.add(prod1);
        products.add(prod2);
        products.add(prod3);

        shop = new OnlineStore(products);
    }

    @Before public void instantiateUserAndSession() {
        user = new User("User1", "1234");
        // register user to have an active session
        LoginSystem sys = new LoginSystem(shop);
        sys.register(user.getUsername(), "1234");
    }

    @Test
    public void testAddingToCart() {
        // retrieve session of User1
        for (ActiveSession current : shop.getSessions().values()) {
            if (current.getUsername().equals(user.getUsername())) {
                Assert.assertEquals(2, current.addToCart(shop.getProducts().get(2), 2));
                break;
            }
        }
    }

    @Test public void testModifyingQuantityInCart() {
        shop.addToCart("User1", shop.getProducts().getFirst(), 2);
        for (ActiveSession current : shop.getSessions().values()) {
            if (current.getUsername().equals(user.getUsername())) {
                Assert.assertEquals(1, current.addToCart(shop.getProducts().getFirst(), 5));
                break;
            }
        }
    }

    @Test public void testCheckout() {
        shop.addToCart("User1", shop.getProducts().getFirst(), 3);
        shop.addToCart("User1", shop.getProducts().getLast(), 2);
        Assert.assertEquals("Your total is 131.0", shop.checkout("User1"));
    }

    @Test public void testSortingProductsByName() {
        List<Product> manuallySorted = new ArrayList<>();
        manuallySorted.add(new Product("Product 1", 13.5D));
        manuallySorted.add(new Product("Product 2", 45.25D));
        manuallySorted.add(new Product("Product 3", 97D));
        Assert.assertArrayEquals(manuallySorted.toArray(), shop.getProductsSorted(Comparator.comparing(Product::getName)).toArray());
    }

    @Test public void testSortingProductsByPrice() {
        List<Product> manuallySorted = new ArrayList<>();
        manuallySorted.add(new Product("Product 1", 13.5D));
        manuallySorted.add(new Product("Product 2", 45.25D));
        manuallySorted.add(new Product("Product 3", 97D));
        Assert.assertArrayEquals(manuallySorted.toArray(), shop.getProductsSorted(Comparator.comparing(Product::getPrice)).toArray());
    }
}
