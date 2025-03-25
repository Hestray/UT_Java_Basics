package aut.isp.lab4.exercise1;

import org.junit.Assert;
import org.junit.Test;

public class AquariumControllerTest {
    //implement minimal tests
    @Test
    public void AquariumControllerTest() {
        AquariumController controller1 = new AquariumController("Neptune Systems", "Trident & A3 Apex Pro", 13.42F);
        AquariumController controller2 = new AquariumController("Coralvue", "HYDROS Control XS");

        controller2.setCurrentTime(8.31F);

        Assert.assertEquals("Aquarium manufacturer: Neptune Systems\t\t model: Trident & A3 Apex Pro\t\t created at: 13.42", controller1.toString());
        Assert.assertEquals("Aquarium manufacturer: Coralvue\t\t model: HYDROS Control XS\t\t created at: 8.31", controller2.toString());
    }
}
