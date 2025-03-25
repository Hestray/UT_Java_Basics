package isp.lab5.exercise2;

public enum chargingWatchType {
    MAGNETIC("Magnetic Charging"),
    WIRELESS("Wireless Charging");

    private String display;
    chargingWatchType(String display) { this.display = display; }
    @Override
    public String toString() { return this.display; }
}
