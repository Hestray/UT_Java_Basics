package isp.lab7.safehome;

public class TenantNotFoundException extends Exception {
    public TenantNotFoundException() {
        System.out.println("Tenant could not be found.");
    }
}
