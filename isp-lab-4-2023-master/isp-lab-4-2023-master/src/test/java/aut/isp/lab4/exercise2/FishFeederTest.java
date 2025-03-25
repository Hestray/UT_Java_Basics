package aut.isp.lab4.exercise2;

import org.junit.Assert;
import org.junit.Test;

public class FishFeederTest {
    @Test
    public void FishFeederTest() {
        FishFeeder tank1 = new FishFeeder("FISHNOSH", "New Generation 2024");
        FishFeeder tank2 = new FishFeeder("Ani Mate Inc", "Fish Mate F14", 9);

        Assert.assertEquals("Current number of meals: 14;\t\t Manufacturer: FISHNOSH AND Model: New Generation 2024", tank1.toString());
        Assert.assertEquals("Current number of meals: 9;\t\t Manufacturer: Ani Mate Inc AND Model: Fish Mate F14", tank2.toString());

        for (int i = 0; i < 5; i++)
            tank2.feed();

        Assert.assertEquals("Current number of meals: 4;\t\t Manufacturer: Ani Mate Inc AND Model: Fish Mate F14", tank2.toString());
        tank2.fillUp();
        Assert.assertEquals("Current number of meals: 14;\t\t Manufacturer: Ani Mate Inc AND Model: Fish Mate F14", tank2.toString());
    }
}
