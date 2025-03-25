package aut.isp.lab4.exercise5;

public class Heater extends Actuator {
    @Override
    public void turnOn() {
        super.setOn(true);
        System.out.println("The heater is ON.");
    }
    @Override
    public void turnOff() {
        super.setOn(false);
        System.out.println("The heater is OFF.");
    }
}
