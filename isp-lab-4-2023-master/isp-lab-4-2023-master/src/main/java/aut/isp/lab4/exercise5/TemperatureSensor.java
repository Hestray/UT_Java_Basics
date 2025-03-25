package aut.isp.lab4.exercise5;

public class TemperatureSensor extends Sensor {
    private int value;

    // methods
    public TemperatureSensor() {
        super("°C");
        this.value = 0;
    }
    public TemperatureSensor(int temperature) {
        super("°C");
        this.value  = temperature;
    }
    public TemperatureSensor(int temperature, String manufacturer, String model, String units) {
        super(manufacturer, model, "°C");
        this.value  = temperature;
    }
    public String toString() {
        return "Current temperature is " + this.value + "°C";
    }

    // setters and getters
    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
