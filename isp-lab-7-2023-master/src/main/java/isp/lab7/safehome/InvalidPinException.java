package isp.lab7.safehome;

public class InvalidPinException extends Exception {
    public InvalidPinException() {
        System.out.println("Invalid PIN. Please try again.");
    }
}
