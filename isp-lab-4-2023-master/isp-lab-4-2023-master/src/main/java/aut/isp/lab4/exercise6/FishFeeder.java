package aut.isp.lab4.exercise6;

public class FishFeeder {
    private String manufacturer;
    private String model;
    private int meals;

    // methods
    public FishFeeder() {
        this.manufacturer   = "unknown";
        this.model          = "unknown";
        this.meals          = 0;
    }
    public FishFeeder(int meals) {
        this.manufacturer   = "unknown";
        this.model          = "unknown";
        this.meals          = meals;
    }
    public FishFeeder(int meals, String manufacturer, String model) {
        this.manufacturer   = manufacturer;
        this.model          = model;
        this.meals          = meals;
    }
    public void feed() {
        this.meals--;
        System.out.println("Fish have been fed.");
    }
    public void fillUp() {
        this.meals = 14;
        System.out.println("Meals were set back to maximum.");
    }
    public String toString() {
        return "Current number of meals: " + this.meals + ";\t\t feeder " + this.model + " manufactured by " + this.manufacturer;
    }

    // setters and getters

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMeals(int meals) {
        this.meals = meals;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getMeals() {
        return meals;
    }
}
