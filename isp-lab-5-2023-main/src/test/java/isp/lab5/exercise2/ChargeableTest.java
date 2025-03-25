package isp.lab5.exercise2;

import org.junit.Assert;
import org.junit.Test;

public class ChargeableTest {
    @Test
    public void ChargeableTest() {
        Laptop laptop = new Laptop("ASUS TUF GAMING", 29, chargingLaptopType.USBC);
        SmartWatch watch = new SmartWatch("Apple", 51, chargingWatchType.MAGNETIC);

        laptop.setTotalChargingTime(250, 150, "C");
        Assert.assertEquals(142, laptop.timeLeft(laptop.getBatteryLevel(), laptop.getCharging()));
        watch.setTotalChargingTime(180, 120, "C");
        Assert.assertEquals(73, watch.timeLeft(watch.getBatteryLevel(), watch.getCharging()));
    }
}
