package aut.isp.lab4.exercise4;

public class Lights {
    // attributes
    private boolean isOn;

    // methods
    public Lights() {
        isOn = false;
    }
    public void turnOn() {
        if (this.isOn) System.out.println("Lights were already on.");
        else { this.isOn = true;
            System.out.println("Lights ON"); }
    }
    public void turnOff() {
        if (!this.isOn) System.out.println("Lights were already off.");
        else { this.isOn = false;
            System.out.println("Lights OFF"); }
    }
    @Override
    public String toString() {
        return "Lights are: " + interpretBoolean();
    }
    private String interpretBoolean() {
        String state;
        if (this.isOn) {
            state = "ON";
        }
        else state = "OFF";
        return state;
    }

    // setters and getters
    public void setOn(boolean on) {
        isOn = on;
    }
    public boolean getOn() {
        return this.isOn;
    }
}
