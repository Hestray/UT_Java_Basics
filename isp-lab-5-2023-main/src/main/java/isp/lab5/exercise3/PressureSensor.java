package isp.lab5.exercise3;

public class PressureSensor extends Sensor {
    private double pressure;

    public PressureSensor(String installLocation) {
        this.setInstallLocation(installLocation);
        this.pressure = 0;
    }

    public PressureSensor(String installLocation, double pressure) {
        this.setInstallLocation(installLocation);
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "Pressure is " + this.pressure;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
}
