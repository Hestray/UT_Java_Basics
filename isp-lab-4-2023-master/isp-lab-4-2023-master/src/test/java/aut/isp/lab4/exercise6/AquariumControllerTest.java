package aut.isp.lab4.exercise6;

import org.junit.Assert;
import org.junit.Test;

public class AquariumControllerTest {
    @Test
    public void AquariumControllerTest() {
        AquariumController controller = new AquariumController();
        controller.modifyPH(8.5f);      // will refuse to change the ph
        Assert.assertEquals("The pH is 7.0", controller.getpHsensor().toString());
        controller.getAlarm().turnOff();
        controller.modifyPH(7.4f);    // will change ph
        Assert.assertEquals("The pH is 7.4", controller.getpHsensor().toString());
    }
}
