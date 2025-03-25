package isp.lab5.exercise3;

import java.util.ArrayList;

public class Exercise3 {
    public static void main(String[] args) {
        Sensor T1 = new TemperatureSensor("bedroom", 20);
        Sensor T2 = new TemperatureSensor("kitchen", 19);
        Sensor T3 = new TemperatureSensor("boiler", 40);
        Sensor P1 = new PressureSensor("boiler", 10);
        Sensor P2 = new PressureSensor("electric kettle", 15);
        Sensor T4 = new TemperatureSensor("electric kettle", 100);

        ArrayList<Sensor> sensorsArray = new ArrayList<>();
        sensorsArray.add(T1);
        sensorsArray.add(T2);
        sensorsArray.add(T3);
        sensorsArray.add(T4);
        sensorsArray.add(P1);
        sensorsArray.add(P2);

        MonitoringService sensors = new MonitoringService(sensorsArray);
        double averageTemp  = sensors.getAverageTemperatureSensors();
        double averageAll   = sensors.getAverageAllSensors();


        System.out.println(sensors.toString());
        System.out.println("Average temperature registered in all temperature sensors is: " + averageTemp);
        System.out.println("Average values registered in all sensors is: " + averageAll);
    }
}
