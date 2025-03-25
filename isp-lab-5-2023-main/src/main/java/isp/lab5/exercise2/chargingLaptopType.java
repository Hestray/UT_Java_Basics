package isp.lab5.exercise2;

public enum chargingLaptopType {
    AC("AC Adapters"),
    USBC("USB-PD Charging Technology");

    private String display;
    chargingLaptopType(String display) { this.display = display; }
    @Override
    public String toString() { return this.display; }
}
