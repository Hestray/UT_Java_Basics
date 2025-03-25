package aut.isp.lab4.exercise5;

import org.junit.Assert;
import org.junit.Test;

public class AquariumControllerTest {
    @Test
    public void AquariumControllerTest() {
        AquariumController controller = new AquariumController(8, 13.3f, 11.15f, "Neptune Systems");
        Assert.assertEquals("Heater is currently: OFF", controller.getHeater().toString());
        controller.getTempSensor().setValue(20);    // 20 C degrees
        controller.checkTemperature();              // should activate since the temperature is below the preset
        Assert.assertEquals("Heater is currently: ON", controller.getHeater().toString());

        Assert.assertEquals("Alarm is currently: OFF", controller.getAlarm().toString());
        controller.getLvlSensor().setValue(6.3f);   // 6.3 Pa water level
        controller.checkWaterLevel();               // activates the alarm
        Assert.assertEquals("Alarm is currently: ON", controller.getAlarm().toString());
    }
}
