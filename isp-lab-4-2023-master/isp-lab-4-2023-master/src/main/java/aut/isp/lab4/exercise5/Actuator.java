package aut.isp.lab4.exercise5;

public class Actuator {
    private String manufacturer;
    private String model;
    private boolean isOn;

    // methods
    public Actuator() {
        this.manufacturer   = "unknown";
        this.model          = "unknown";
        this.isOn           = false;
    }
    public Actuator(String manufacturer, String model) {
        this.manufacturer   = manufacturer;
        this.model          = model;
        this.isOn           = false;
    }
    public void turnOn()    { this.isOn = true; }
    public void turnOff()   { this.isOn = false; }
    @Override
    public String toString() {
        String state;
        if (this.isOn)  state = "ON";
        else            state = "OFF";

        if (this instanceof Heater) return "Heater is currently: " + state;
        else if (this instanceof Alarm) return "Alarm is currently: " + state;
        else return "Current state: " + state;
    }

    // setters and getters
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setOn(boolean on) {
        isOn = on;
    }
    public String getManufacturer() { return manufacturer; }
    public String getModel() {
        return model;
    }
    public boolean getIsOn() {
        return isOn;
    }
}
