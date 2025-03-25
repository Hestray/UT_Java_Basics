package isp.lab6.exercise2;

import java.util.Objects;

public class Vehicle {
    private int VIN; // vehicle identification number
    private String licensePlate;
    private String make;
    private String model;
    private int year;

    public Vehicle(int VIN, String licensePlate, String make, String model, int year) {
        this.licensePlate   = licensePlate;
        this.VIN            = VIN;
        this.make           = make;
        this.model          = model;
        this.year           = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle that = (Vehicle) o;
        return VIN == that.VIN;
    }

    @Override
    public int hashCode() {
        return Objects.hash(VIN);
    }

    @Override public String toString() {
        return "VEHICLE: VIN = " + this.VIN + "  License plate: " + this.licensePlate +
                "\tModel: " + this.model + " from make: " + this.make + "; released in year: " + this.year;
    }

    public String getModel() {
        return model;
    }

    public int getVIN() {
        return VIN;
    }

    public int getYear() {
        return year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getMake() {
        return make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setVIN(int VIN) {
        this.VIN = VIN;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
