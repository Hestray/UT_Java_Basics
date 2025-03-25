package aut.isp.lab4.exercise3;

import org.junit.Assert;
import org.junit.Test;

public class FishFeederTest {
    @Test
    public void FishFeederTest() {
        FishFeeder tank1 = new FishFeeder("FISHNOSH", "New Generation 2024");
        FishFeeder tank2 = new FishFeeder("Ani Mate Inc", "Fish Mate F14", 9);

        Assert.assertEquals("Current number of meals left: 14", tank1.toString());
        Assert.assertEquals("Current number of meals left: 9", tank2.toString());

        for (int i = 0; i < 5; i++)
            tank2.feed();

        Assert.assertEquals("Current number of meals left: 4", tank2.toString());
        tank2.fillUp();
        Assert.assertEquals("Current number of meals left: 14", tank2.toString());
    }
}
