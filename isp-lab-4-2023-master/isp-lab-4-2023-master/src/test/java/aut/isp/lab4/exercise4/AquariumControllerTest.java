package aut.isp.lab4.exercise4;

import org.junit.Assert;
import org.junit.Test;

public class AquariumControllerTest {
    @Test
    public void AquariumControllerTest() {
        AquariumController controller = new AquariumController(8.45f, 12.3f, 10.3f, 17.3f, 7);
        Assert.assertEquals("Lights are: OFF", controller.getLights().toString());
        controller.getLights().turnOn();
        Assert.assertEquals("Lights are: ON", controller.getLights().toString());

        Assert.assertEquals("Number of meals: 7\tManufacturer: unknown\tModel: unknown", controller.getFeeder().toString());
        while (controller.getFeeder().getMeals() != 0)
            controller.getFeeder().feed();
        Assert.assertEquals("Number of meals: 0\tManufacturer: unknown\tModel: unknown", controller.getFeeder().toString());
        controller.getFeeder().fillUp();
        Assert.assertEquals("Number of meals: 14\tManufacturer: unknown\tModel: unknown", controller.getFeeder().toString());
    }
}
