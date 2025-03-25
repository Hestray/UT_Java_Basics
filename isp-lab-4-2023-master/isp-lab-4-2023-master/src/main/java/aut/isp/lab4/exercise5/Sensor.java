package aut.isp.lab4.exercise5;

public class Sensor{
    private String manufacturer;
    private String model;
    private String unitOfMeasurement;
    public Sensor(String unitOfMeasurement) {
        this.unitOfMeasurement  = unitOfMeasurement;
        this.manufacturer       = "unknown";
        this.model              = "unknown";
    }
    public Sensor(String manufacturer, String model, String unitOfMeasurement) {
        this.unitOfMeasurement  = unitOfMeasurement;
        this.manufacturer       = manufacturer;
        this.model              = model;
    }
    @Override
    public String toString() {
        return "Model: " + model + " ; Manufacturer: " + manufacturer + "\t\t Data is displayed in units of: " + unitOfMeasurement;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }
}
