package aut.isp.lab4.exercise3;

public class AquariumController {

    //attributes
    private String  manufacturer;
    private String  model;
    private float   currentTime;
    private float   feedingTime;
    FishFeeder      feeder;

    //constructors
    public AquariumController(String manufacturer, String model) {
        this.manufacturer   = manufacturer;
        this.model          = model;
        feeder              = new FishFeeder();
    }

    public AquariumController(String manufacturer, String model, float currentTime) {
        this.manufacturer   = manufacturer;
        this.model          = model;
        this.currentTime    = currentTime;
        feeder              = new FishFeeder();
    }

    //methods

    public void setCurrentTime(float currentTime) {
        this.currentTime    = currentTime;
    }

    public void setFeedingTime(float feedingTime) {
        this.feedingTime    = feedingTime;
    }

    public float getFeedingTime() {
        return feedingTime;
    }

    public float getCurrentTime() {
        return currentTime;
    }

    public String toString() {
        return "Current time is: " + currentTime + ";\t\t Feeding time is at: " + feedingTime;
    }
}