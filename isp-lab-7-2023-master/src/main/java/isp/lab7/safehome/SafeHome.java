package isp.lab7.safehome;

import java.util.HashMap;

public class SafeHome {
    public static void main(String[] args) {
        UserInterface software = new UserInterface(new HashMap<>());
        software.menu();
    }
}
