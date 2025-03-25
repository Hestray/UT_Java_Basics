package isp.lab5.exercise3;

public class TemperatureSensor extends Sensor {
    private double temperature;
    public TemperatureSensor(String installLocation) {
        this.setInstallLocation(installLocation);
        this.temperature = 0d;
    }
    public TemperatureSensor(String installLocation, double temperature) {
        this.setInstallLocation(installLocation);
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Temperature is " + this.temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
