package aut.isp.lab4.exercise1;

public class AquariumController {

    //attributes
    private String  manufacturer;
    private String  model;
    private float   currentTime;

    //constructors
    public AquariumController(String manufacturer, String model) {
        this.manufacturer   = manufacturer;
        this.model          = model;
    }

    public AquariumController(String manufacturer, String model, float currentTime) {
        this.manufacturer   = manufacturer;
        this.model          = model;
        this.currentTime    = currentTime;
    }

    //methods

    public float setCurrentTime(float currentTime) {
        this.currentTime    = currentTime;
        return currentTime;
    }

    public String toString() {
        return "Aquarium manufacturer: " + manufacturer + "\t\t model: " + model + "\t\t created at: " + currentTime;
    }
}
