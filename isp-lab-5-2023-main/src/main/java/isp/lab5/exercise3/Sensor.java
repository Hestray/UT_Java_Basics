package isp.lab5.exercise3;

abstract class Sensor {
    private String installLocation;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setInstallLocation(String installLocation) {
        this.installLocation = installLocation;
    }

    public String getName() {
        return name;
    }

    public String getInstallLocation() {
        return installLocation;
    }
}
