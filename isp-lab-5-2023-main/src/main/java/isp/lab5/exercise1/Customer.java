package isp.lab5.exercise1;

public class Customer {
    private String customerId;
    private String name;
    private String phone;
    Address address = new Address();

    // methods
    public Customer() {
        this.name   = "";
        this.phone  = "";
    }
    public Customer(String name, String phone, String city, String street) {
        this.name   = name;
        this.phone  = phone;
        this.address.setCity(city);
        this.address.setStreet(street);
    }
    @Override
    public String toString() {
        return "CUSTOMER: " + this.name + "\t\tphone: " + this.phone + "\t\taddress: " + this.address.getCity() + " " + this.address.getStreet();
    }

    // setters and getters
    public void setCustomerId(String customerId) {
        this.customerId = this.name + this.phone + this.address.getCity() + this.address.getStreet();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getPhone() {
        return phone;
    }
}
