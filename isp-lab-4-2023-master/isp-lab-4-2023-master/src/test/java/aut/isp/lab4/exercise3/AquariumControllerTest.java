package aut.isp.lab4.exercise3;

import org.junit.Assert;
import org.junit.Test;

public class AquariumControllerTest {
    //implement minimal tests
    @Test
    public void AquariumControllerTest() {
        AquariumController controller1 = new AquariumController("Neptune Systems", "Trident & A3 Apex Pro", 13.42F);
        AquariumController controller2 = new AquariumController("Coralvue", "HYDROS Control XS");

        controller2.setCurrentTime(8.31F);
        controller2.setFeedingTime(13.15F);
        controller1.setFeedingTime(9.34F);

        Assert.assertEquals("Current time is: 13.42;\t\t Feeding time is at: 9.34", controller1.toString());
        Assert.assertEquals("Current time is: 8.31;\t\t Feeding time is at: 13.15", controller2.toString());
    }
}