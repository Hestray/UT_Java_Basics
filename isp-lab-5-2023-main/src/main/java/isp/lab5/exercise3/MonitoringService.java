package isp.lab5.exercise3;

import java.util.ArrayList;

public class MonitoringService {
    private ArrayList<Sensor> sensors = new ArrayList<>();  // maximum values to be contained: 10

    // methods
    public MonitoringService(Sensor sensor) {
        if (this.sensors.size() <= 9)
            this.sensors.add(sensor);
        else
            System.err.println("ArrayList<Sensor> exceeds limit size of 10");
    }

    public MonitoringService(ArrayList<Sensor> sensors) {
        if (sensors.size() <= 10)
            this.sensors = sensors;
        else System.err.println("ArrayList<Sensor> exceeds limit size of 10");
    }

    public double getAverageTemperatureSensors() {
        if (!this.sensors.isEmpty())
        {
            double avg = 0;
            int count = 0;
            for (int i = 0; i < this.sensors.size(); i++)
                if (this.sensors.get(i) instanceof TemperatureSensor) {
                    avg += ((TemperatureSensor) this.sensors.get(i)).getTemperature();
                    count++;
                }
            avg = avg / count;
            return avg;
        }
        else {
            System.err.println("There are no sensors.");
            return 0;
        }
    }

    public double getAverageAllSensors() {
        if (!this.sensors.isEmpty())
        {
            double avg = 0;
            int count = 0;
            for (Sensor sensor : this.sensors)
                if (sensor instanceof TemperatureSensor) {
                    avg += ((TemperatureSensor) sensor).getTemperature();
                    count++;
                } else if (sensor instanceof PressureSensor) {
                    avg += ((PressureSensor) sensor).getPressure();
                    count++;
                }
            avg = avg / count;
            return avg;
        }
        else {
            System.err.println("There are no sensors.");
            return 0;
        }
    }

    @Override
    public String toString() {
        if (this.sensors.isEmpty()) return "There are no sensors";
        else {
            String message;
            message = "There are " + this.sensors.size() + " sensors in the following locations: ";
            for (int i = 0; i < this.sensors.size(); i++) {
                message = message + this.sensors.get(i).getInstallLocation();
                if (i != this.sensors.size()-1) message = message + ", ";
            }
            return message;
        }
    }

}
