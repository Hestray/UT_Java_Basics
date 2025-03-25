package aut.isp.lab4.exercise5;

public class Exercise5 {
    public static void main(String[] args) {
        AquariumController controller1 = new AquariumController();
        AquariumController controller2 = new AquariumController(10, 12.30f, 17.15f, "Neptune Systems");

        controller1.getTempSensor().setValue(23);
        controller1.getLvlSensor().setValue(6.7f);
        System.out.println(controller1.toString());

        for (int a = 5; a > 0; a--) {
            System.out.println(controller1.getTempSensor().toString());
            System.out.println(controller1.getLvlSensor().toString());
            if (controller1.getTempSensor().getValue() != controller1.getPresetTemperature()) {
                controller1.checkTemperature();
                controller1.getTempSensor().setValue(controller1.getTempSensor().getValue() + 1);
            }
            else controller1.getHeater().turnOff();
            if (controller1.getLvlSensor().getValue() != controller1.getPresetLevel()) {
                controller1.checkWaterLevel();
                controller1.getLvlSensor().setValue(controller1.getLvlSensor().getValue() + 1);
            }
            else controller1.getAlarm().turnOff();
        }

        for (int i = 2; i > 0; i--) {
            if (controller1.getFeeder().getMeals() > 0) controller1.getFeeder().feed();
            else controller1.getFeeder().fillUp();
            System.out.println(controller1.getFeeder().toString());
        }

        controller2.getTempSensor().setValue(19);
        controller2.getLvlSensor().setValue(8.3f);
        System.out.println(controller2.toString());

        for (int b = 8; b > 0; b--) {
            System.out.println(controller2.getTempSensor().toString());
            System.out.println(controller2.getLvlSensor().toString());
            if (controller2.getTempSensor().getValue() != controller2.getPresetTemperature()) {
                controller2.checkTemperature();
                controller2.getTempSensor().setValue(controller2.getTempSensor().getValue() + 1);
            }
            else controller2.getHeater().turnOff();
            if (controller2.getLvlSensor().getValue() != controller2.getPresetLevel()) {
                controller2.checkWaterLevel();
                controller2.getLvlSensor().setValue(controller2.getLvlSensor().getValue() + 1);
            }
            else controller2.getAlarm().turnOff();
        }

        for (int j = 9; j > 0; j--) {
            if (controller2.getFeeder().getMeals() > 0) controller2.getFeeder().feed();
            else controller2.getFeeder().fillUp();
            if (j == 7) controller2.getFeeder().fillUp();
        }
    }
}
