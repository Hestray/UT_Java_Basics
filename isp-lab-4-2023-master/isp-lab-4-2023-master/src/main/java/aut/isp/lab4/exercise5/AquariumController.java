package aut.isp.lab4.exercise5;

public class AquariumController {
    private String manufacturer;
    private String model;
    private float currentTime;
    private float feedingTime;
    private int presetTemperature;
    private float presetLevel;
    private FishFeeder feeder            = new FishFeeder();
    private LevelSensor lvlSensor        = new LevelSensor();
    private TemperatureSensor tempSensor = new TemperatureSensor();
    private Actuator alarm               = new Alarm();
    private Actuator heater              = new Heater();

    // methods
    public AquariumController() {
        this.manufacturer       = "unknown";
        this.model              = "unknown";
        this.presetTemperature  = 25;
        this.presetLevel        = 7.0f;
        this.feedingTime        = 12.3f;
        this.currentTime        = 0.0f;
    }
    public AquariumController(int meals, float feedingTime, float currentTime) {
        this.manufacturer       = "unknown";
        this.model              = "unknown";
        this.presetTemperature  = 25;
        this.presetLevel        = 7.0f;
        this.currentTime        = currentTime;
        this.feedingTime        = feedingTime;
        this.feeder.setMeals(meals);
    }
    public AquariumController(int meals, float feedingTime, float currentTime, String manufacturer) {
        this.manufacturer       = manufacturer;
        this.model              = manufacturer;
        this.presetTemperature  = 25;
        this.presetLevel        = 7.0f;
        this.currentTime        = currentTime;
        this.feedingTime        = feedingTime;
        this.feeder.setManufacturer(manufacturer);
        this.feeder.setModel(manufacturer);
        this.feeder.setMeals(meals);
    }
    public AquariumController(int meals, float feedingTime, float currentTime, String manufacturer, String model) {
        this.manufacturer       = manufacturer;
        this.model              = model;
        this.presetTemperature  = 25;
        this.presetLevel        = 7.0f;
        this.currentTime        = currentTime;
        this.feedingTime        = feedingTime;
        this.feeder.setManufacturer(manufacturer);
        this.feeder.setModel(model);
        this.feeder.setMeals(meals);
    }
    public void checkTemperature() {
        if (this.tempSensor.getValue() < this.presetTemperature)
            this.heater.turnOn();
        else if (this.tempSensor.getValue() == this.presetTemperature)
            this.heater.turnOff();

        System.out.println(this.heater.toString());
    }
    public void checkWaterLevel() {
        System.out.println(this.lvlSensor.toString());
        if (this.lvlSensor.getValue() < this.presetLevel) {
            this.alarm.turnOn();
            System.out.println("Please raise the water level.");
        }
    }
    @Override
    public String toString() {
        return "Aquarium " + this.manufacturer + " " + this.model + " having the temperature preset to " + this.presetTemperature + " and water level " + this.presetLevel;
    }

    // setters and getters
    public void setCurrentTime(float currentTime) {
        this.currentTime = currentTime;
    }

    public void setFeedingTime(float feedingTime) {
        this.feedingTime = feedingTime;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setAlarm(Actuator alarm) {
        this.alarm = alarm;
    }

    public void setHeater(Actuator heater) {
        this.heater = heater;
    }

    public void setLvlSensor(LevelSensor lvlSensor) {
        this.lvlSensor = lvlSensor;
    }

    public void setPresetLevel(float presetLevel) {
        this.presetLevel = presetLevel;
    }

    public void setPresetTemperature(int presetTemperature) {
        this.presetTemperature = presetTemperature;
    }

    public void setTempSensor(TemperatureSensor tempSensor) {
        this.tempSensor = tempSensor;
    }

    public void setFeeder(FishFeeder feeder) {
        this.feeder = feeder;
    }

    public TemperatureSensor getTempSensor() {
        return tempSensor;
    }

    public float getPresetLevel() {
        return presetLevel;
    }

    public int getPresetTemperature() {
        return presetTemperature;
    }

    public float getCurrentTime() {
        return currentTime;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public float getFeedingTime() {
        return feedingTime;
    }

    public String getModel() {
        return model;
    }

    public Actuator getAlarm() {
        return alarm;
    }

    public Actuator getHeater() {
        return heater;
    }

    public LevelSensor getLvlSensor() {
        return lvlSensor;
    }

    public FishFeeder getFeeder() {
        return feeder;
    }
}
