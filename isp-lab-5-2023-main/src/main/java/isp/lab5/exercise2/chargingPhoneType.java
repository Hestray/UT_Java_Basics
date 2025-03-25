package isp.lab5.exercise2;

public enum chargingPhoneType {
    STANDARD("Standard Charging"),
    FAST("Fast Charging"),
    WIRELESS("Wireless Charging");


    private String display;
    chargingPhoneType(String display) { this.display = display; }
    @Override
    public String toString() { return this.display; }
}
