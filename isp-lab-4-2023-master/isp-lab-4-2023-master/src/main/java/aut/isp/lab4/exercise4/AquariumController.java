package aut.isp.lab4.exercise4;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AquariumController {
    // attributes
    private FishFeeder  feeder;
    private String      manufacturer;
    private String      model;
    private float       currentTime;
    private float       feedingTime;
    private float       lightsOffTime;
    private float       lightOnTime;
    private Lights      lights;

    // methods
    public AquariumController() {
        this.currentTime    = (float)LocalDateTime.now().getHour() + LocalDateTime.now().getMinute() / 100.0f;
        this.feedingTime    = 12.3f;
        this.lightOnTime    = 11.0f;
        this.lightsOffTime  = 18.0f;
        this.feeder         = new FishFeeder();
        this.lights         = new Lights();
    }
    public AquariumController(float currentTime, float feedingTime, float lightOnTime, float lightsOffTime, int meals) {
        this.currentTime    = currentTime;
        this.feedingTime    = feedingTime;
        this.lightOnTime    = lightOnTime;
        this.lightsOffTime  = lightsOffTime;
        this.feeder         = new FishFeeder(meals);
        this.lights         = new Lights();
    }
    public AquariumController(float currentTime, float feedingTime, float lightOnTime, float lightsOffTime, int meals, String manufacturer, String model) {
        this.currentTime    =  currentTime;
        this.feedingTime    = feedingTime;
        this.lightOnTime    = lightOnTime;
        this.lightsOffTime  = lightsOffTime;
        this.manufacturer   = manufacturer;
        this.model          = model;
        this.feeder         = new FishFeeder(meals, manufacturer, model);
        this.lights         = new Lights();
    }

    @Override
    public String toString() {
        return "Aquarium manufacturer: " + this.manufacturer + "\tModel: " + this.model + "\tFeeding time: " + this.feedingTime + "\tLights: " + this.lights.getOn();
    }

    // setters and getters

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setFeedingTime(float feedingTime) {
        this.feedingTime = feedingTime;
    }

    public void setCurrentTime(float currentTime) {
        this.currentTime = currentTime;
    }

    public void setFeeder(FishFeeder feeder) {
        this.feeder = feeder;
    }

    public void setLightOnTime(float lightOnTime) {
        this.lightOnTime = lightOnTime;
    }

    public void setLights(Lights lights) {
        this.lights = lights;
    }

    public void setLightsOffTime(float lightsOffTime) {
        this.lightsOffTime = lightsOffTime;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public float getCurrentTime() {
        return currentTime;
    }

    public float getFeedingTime() {
        return feedingTime;
    }

    public FishFeeder getFeeder() {
        return feeder;
    }

    public float getLightOnTime() {
        return lightOnTime;
    }

    public float getLightsOffTime() {
        return lightsOffTime;
    }

    public Lights getLights() {
        return lights;
    }
}
