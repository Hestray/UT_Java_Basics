package isp.lab7.safehome;

public class TenantAlreadyExistsException extends Exception {
    public TenantAlreadyExistsException() {
        System.out.println("Tenant already exists and cannot be added again.");
    }
}
