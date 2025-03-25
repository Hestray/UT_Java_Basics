package isp.lab5.exercise2;

import java.util.Random;

public class SmartWatch implements Chargeable {
    private int         batteryLevel;
    private int         totalChargingTime;
    private String      model;
    chargingWatchType  charging;

    // methods
    public SmartWatch(int batteryLevel) { this.batteryLevel = batteryLevel; }
    public SmartWatch(String model, int batteryLevel) {
        this.model          = model;
        this.batteryLevel   = batteryLevel;
    }
    public SmartWatch(String model, int batteryLevel, chargingWatchType charging) {
        this.model          = model;
        this.batteryLevel   = batteryLevel;
        this.charging       = charging;
    }

    // used to generate the time for charge(durationInMinutes)
    public int timeLeft(int batteryLevel, chargingWatchType charging) {
        int chargingLeft = 100 - batteryLevel;
        double time = 0f;

        if (this.totalChargingTime == 0) {
            if (charging.equals(chargingWatchType.MAGNETIC)) {
                setTotalChargingTime(150, 90, "R");
            } else if (charging.equals(chargingWatchType.WIRELESS)) {
                setTotalChargingTime(180, 120, "R");
            }
            else { System.err.println("invalid choice of charger"); return 0; }
        }

        time = this.totalChargingTime / 100.0 * chargingLeft;
        return (int)time;
    }

    @Override
    public void charge(int durationInMinutes) {
        int hour = 0;
        while (durationInMinutes >= 60) {
            hour++;
            durationInMinutes -= 60;
        }
        if (durationInMinutes >= 60)
            System.err.println("invalid charging time left");
        else {
            if (durationInMinutes == 0)
                System.out.println("Your watch has 0" + hour + ":00 left until fully charged.");
            else {
                if (durationInMinutes < 10 && durationInMinutes > 0) {
                    durationInMinutes *= 10;
                }
                System.out.println("Your watch has 0" + hour + ":" + durationInMinutes + " left until fully charged.");
            }
        }
    }

    @Override
    public int getBatteryLevel() {      // getter
        return batteryLevel;
    }

    @Override
    public String toString() {
        return "SMARTWATCH MODEL: " + this.model + "\t\tBATTERY STATUS: " + this.batteryLevel +
                "\t\tCHARGING TYPE: " + this.charging;
    }

    // setters and getters

    public void setTotalChargingTime(int maxChargingTime, int minChargingTime, String mode) {
        if (mode.equals("R")) {
            if (this.totalChargingTime == 0) {
                Random random = new Random();
                this.totalChargingTime = random.nextInt(maxChargingTime - minChargingTime + 1) + minChargingTime;
            }
        } else
            if (mode.equals("C"))
                this.totalChargingTime = (maxChargingTime + minChargingTime) / 2;
    }

    public int getTotalChargingTime() {
        this.timeLeft(this.batteryLevel, this.charging);
        return this.totalChargingTime;
    }

    public void setCharging(chargingWatchType charging) {
        this.charging = charging;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public chargingWatchType getCharging() {
        return charging;
    }

    public String getModel() {
        return model;
    }
}
