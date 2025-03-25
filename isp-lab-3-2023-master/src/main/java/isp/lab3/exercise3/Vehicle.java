package isp.lab3.exercise3;

public class Vehicle {
    private String model;
    private String type;
    private int speed;
    char fuelType;
    static int vehicleNumber = 0;

    public Vehicle(String m, String t, int s, char fT) {
        model       = m;
        type        = t;
        speed       = s;
        fuelType    = fT;
        vehicleNumber++;
    }

    public static int displayVehicleManufactured() {
        return vehicleNumber;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setFuelType(char fuelType) {
        this.fuelType = fuelType;
    }

    public char getFuelType() {
        return fuelType;
    }

    public String toString() {
            return (model + " (" + type + ") speed " + speed + " fuel type " + fuelType);
    }

    public static void main(String[] args) {
        Vehicle v1 = new Vehicle("hyundai", "I40", 220, 'B');
        Vehicle v2 = new Vehicle("renault", "kardian", 230, 'D');

        v1.setModel("Hyundai"); v1.setSpeed(230); v1.setType("i40"); v1.setFuelType('D');
        v2.setModel("Renault"); v2.setSpeed(220); v2.setType("Kardian"); v2.setFuelType('B');

        System.out.println("First vehicle is \t MODEL " + v1.getModel() + "\t TYPE " + v1.getType() + "\t\t PEAK SPEED " + v1.getSpeed() + "\t FUEL " + v1.getFuelType());
        System.out.println("Short description: " + v1.toString());
        System.out.println("--------------------------------------------------------------");
        System.out.println("Second vehicle is \t MODEL " + v2.getModel() + "\t TYPE " + v2.getType() + "\t PEAK SPEED " + v2.getSpeed() + "\t FUEL " + v2.getFuelType());
        System.out.println("Short description: " + v2.toString());
        System.out.println("--------------------------------------------------------------");
        System.out.println("Are they equal? The answer is " + v1.equals(v2) + "!");
        System.out.println("For now, we have a total number of " + displayVehicleManufactured() + " cars!");
    }
}
