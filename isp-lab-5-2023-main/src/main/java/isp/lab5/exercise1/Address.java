package isp.lab5.exercise1;

public class Address {
    private String street;
    private String city;

    // methods
    public Address() {
        this.street = "unknown";
        this.city   = "unknown";
    }
    public Address(String street, String city) {
        this.street = street;
        this.city   = city;
    }

    // setters and getters

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }
}
