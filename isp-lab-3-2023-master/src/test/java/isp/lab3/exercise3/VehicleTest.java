package isp.lab3.exercise3;

import isp.lab3.exercise3.Vehicle;
import org.junit.Assert;
import org.junit.Test;

public class VehicleTest {
    @Test
    public void vehicleTest() {
        Vehicle v = new Vehicle("Hyundai", "i20", 220, 'D');

        Assert.assertEquals("Hyundai (i20) speed 220 fuel type D", v.toString());
    }
}
