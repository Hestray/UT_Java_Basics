package isp.lab5.exercise3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class monitoringServiceTest {
    @Test
    public void monitoringServiceTest() {
        Sensor T1 = new TemperatureSensor("bedroom", 24);
        Sensor T2 = new TemperatureSensor("kitchen", 20);
        Sensor P1 = new PressureSensor("boiler", 40);
        Sensor P2 = new PressureSensor("electric kettle", 12);

        ArrayList<Sensor> sensorsArray = new ArrayList<>();
        sensorsArray.add(T1);
        sensorsArray.add(T2);
        sensorsArray.add(P1);
        sensorsArray.add(P2);

        MonitoringService sensors = new MonitoringService(sensorsArray);

        Assert.assertEquals(22.0d, sensors.getAverageTemperatureSensors(), 0);
        Assert.assertEquals(24.0d, sensors.getAverageAllSensors(), 0);
        Assert.assertEquals("There are 4 sensors in the following locations: bedroom, kitchen, boiler, electric kettle", sensors.toString());
    }
}
