package aut.isp.lab4.exercise3;

public class FishFeeder {
    // attributes
    private String  manufacturer;
    private String  model;
    private int     meals;

    // constructors
    public FishFeeder() {
        this.manufacturer   = "unknown";
        this.model          = "unknown";
        this.meals          = 14;
    }
    public FishFeeder(String manufacturer, String model) {
        this.manufacturer   = manufacturer;
        this.model          = model;
        this.meals          = 14;
    }
    public FishFeeder(String manufacturer, String model, int meals) {
        this.manufacturer   = manufacturer;
        this.model          = model;
        this.meals          = meals;    // set the number of meals the fish feeder came out of shop with
    }

    // methods
    public void feed() {
        meals--;
        System.out.println("Your fish have been fed.");
    }

    public void fillUp() {
        this.meals = 14;
        System.out.println("Meals have been filled back up! Enjoy~");
    }

    @Override
    public String toString() {
        return "Current number of meals left: " + meals;
    }
}