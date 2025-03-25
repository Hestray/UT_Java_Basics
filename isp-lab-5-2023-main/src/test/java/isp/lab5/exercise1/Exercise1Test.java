package isp.lab5.exercise1;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Radu Miron
 * @version 1
 */
public class Exercise1Test {
    @Test
    public void Exercise1Test() {
        Product     p = new Product ("2","Succulent plant",2d, ProductCategory.HOME_AND_GARDEN);
        Customer    c = new Customer("Luna", "7768", "Nari","Orayama street 54th");
        Order       o = new Order   ("1", p, c);
        Address     a = new Address ();

        Assert.assertEquals(2d, o.getTotalPrice(), 0);
        Assert.assertEquals("2\t\tProduct: Succulent plant\t\tFrom category: Home_and_Garden\t\tPrice: 2.0", p.toString());
        Assert.assertEquals("unknown unknown", a.getCity() + " " + a.getStreet());
    }
}
