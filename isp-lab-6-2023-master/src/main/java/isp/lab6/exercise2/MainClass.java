package isp.lab6.exercise2;

public class MainClass {
    public static void main(String[] args) {
        Vehicle v1 = new Vehicle(1, "BV55APE", "Hyundai", "i40", 2018);
        Vehicle v2 = new Vehicle(1, "B404KEK", "Dacia", "Logan", 1514);
        Vehicle v3 = new Vehicle(2, "PH32POM", "Renault", "CLIO", 1000);
        Vehicle v4 = new Vehicle(3, "CJ99BOO", "BMW", "i7", 2022);

        RegistrySystem rs = new RegistrySystem();

        // add the vehicles to the system
        if (rs.addVehicle(v1))
            System.out.println("Vehicle v1 was added successfully.");
        else System.out.println("Vehicle could not be added.");
        if (rs.addVehicle(v2))
            System.out.println("Vehicle v2 was added successfully.");
        else System.out.println("Vehicle could not be added.");
        if (rs.addVehicle(v3))
            System.out.println("Vehicle v3 was added successfully.");
        else System.out.println("Vehicle could not be added.");
        if (rs.addVehicle(v4))
            System.out.println("Vehicle v4 was added successfully.");
        else System.out.println("Vehicle could not be added.");

        // display all the current vehicles
        rs.displayVehicles();

        // remove v2 from the registry
        if (rs.removeVehicle(2))
            System.out.println("Vehicle identified by VIN " +  v2.getVIN() + " was successfully removed.");
        else System.out.println("Vehicle could not be removed.");

        // display all the current vehicles
        rs.displayVehicles();
    }
}
