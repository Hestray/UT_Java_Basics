package aut.isp.lab4.exercise6;

public class pHmonitor {
    private float value;

    // methods
    public pHmonitor() {
        this.value = 7;         // basic pH
    }
    public pHmonitor(float pH) {
        this.value = pH;
    }
    public String toString() {
        return "The pH is " + this.value;
    }

    // getters and setters
    public void setValue(float value) {
        this.value = value;
    }
    public float getValue() {
        return value;
    }
}
