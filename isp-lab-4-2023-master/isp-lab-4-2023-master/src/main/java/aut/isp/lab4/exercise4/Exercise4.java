package aut.isp.lab4.exercise4;

import java.util.Scanner;

public class Exercise4 {
    public static void feeding(AquariumController controller) {
        if (controller.getCurrentTime() > controller.getFeedingTime())
            controller.getFeeder().feed();
        else System.out.println("Not yet feeding time...");
    }
    public static void lighting(AquariumController controller) {
        if (controller.getLightsOffTime() < controller.getCurrentTime()) {
            controller.getLights().turnOff();
            System.out.println("Lights are now off.");
        }
        else {
            if (controller.getLightOnTime() < controller.getCurrentTime()) {
                controller.getLights().turnOn();
                System.out.println("Lights are now on.");
            }
        }
    }
    public static void main(String[] args) {
        AquariumController controller1 = new AquariumController();
        AquariumController controller2 = new AquariumController(15.40f, 20.55f, 10.15f, 16.3f, 9, "Neptune Systems", "ApexEL Aquarium Controller");

        // changing time and playing with it
        controller1.setCurrentTime(21.04f);
        feeding(controller1);

        // playing with the lights
        lighting(controller1);

        // changing time
        controller1.setCurrentTime(10.45f);
        controller2.setCurrentTime(10.45f);

        feeding(controller1);
        lighting(controller2);

        controller1.toString();
        controller2.toString();
        controller2.getFeeder().toString();
        controller2.getLights().toString();

        // manually feeding, filling meals and playing with lights
        for (int i = 13; i >= 0; i--) {
            controller2.setCurrentTime(controller2.getCurrentTime()+0.35f);     // advance the time

            // manual feed
            if (controller2.getFeeder().getMeals() == 0) controller2.getFeeder().fillUp();
            controller2.getFeeder().feed();
            controller2.getLights().turnOff();

            // output time and other information
            controller2.getFeeder().toString();
            controller2.getLights().toString();
        }
    }
}
