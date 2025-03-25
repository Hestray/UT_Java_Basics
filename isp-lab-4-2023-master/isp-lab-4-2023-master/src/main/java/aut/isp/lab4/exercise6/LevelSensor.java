package aut.isp.lab4.exercise6;

public class LevelSensor extends Sensor {
    private float value;

    // methods
    public LevelSensor() {
        super("Pa");    // measuring water level using Pascal (for pressure)
        this.value = 0; }
    public LevelSensor(float value) { super("Pa"); this.value = value; }
    public LevelSensor(float value, String manufacturer, String model, String units) { super(manufacturer, model, units); this.value = value; }
    @Override
    public String toString() { return "The current water level is " + value; }

    // setter and getter
    public float getValue() {
        return value;
    }
    public void setValue(float value) {
        this.value = value;
    }
}
