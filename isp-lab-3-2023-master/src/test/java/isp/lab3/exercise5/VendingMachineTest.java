package isp.lab3.exercise5;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {
    @Test
    public void VendingMachineTest() {
        String[]    products    = {"sandwich", "chocolate bar", "crackers"};
        int[]       prices      = {15, 4, 5};
        VendingMachine prod = new VendingMachine(products, prices, "R");

        Assert.assertEquals(1, prod.displayProducts());
        Assert.assertEquals(0, prod.displayCredit());

        prod.insertCoin(13);

        Assert.assertEquals(13, prod.displayCredit());
        Assert.assertEquals("chocolate bar", prod.selectProduct(1));
    }
}
