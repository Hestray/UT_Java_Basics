package aut.isp.lab4.exercise4;

public class FishFeeder {
    // attributes
    private String  manufacturer;
    private String  model;
    private int     meals;
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
        if (this.meals > 0) {
            this.meals--;
            System.out.println("Fish have been fed. Meals left: " + this.meals);
        }
        else
            System.out.println("Couldn't feed the fish. Please refill the meals.");
    }

    public void fillUp() {
        if (this.meals == 14) {
            System.out.println("Can't refill. The feeder is already full.");
        }
        else
        {
            this.meals = 14;
            System.out.println("Feeder has been refilled.");
        }
    }
    @Override
    public String toString() {
        return "Number of meals: " + this.meals + "\tManufacturer: " + this.manufacturer + "\tModel: " + this.model;
    }
    // setters and getters

    public void setModel(String model) {
        this.model = model;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setMeals(int meals) {
        this.meals = meals;
    }

    public String getModel() {
        return model;
    }

    public int getMeals() {
        return meals;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
