package isp.lab7.safehome;

public class TooManyAttemptsException extends Exception{
    public TooManyAttemptsException() {
        System.out.println("Too many attempts. Alarm ON.");
    }
}
