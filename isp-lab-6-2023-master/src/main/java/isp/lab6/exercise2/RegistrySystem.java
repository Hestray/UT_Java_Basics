package isp.lab6.exercise2;

import java.util.HashSet;
import java.util.Set;

public class RegistrySystem {
    private Set<Vehicle> vehicles = new HashSet<>();

    /**
     * This method will add a vehicle in the register if its VIN is unique.
     * @param v is the vehicle to be added
     * @return false if the vehicle could not be added (VIN is not unique) or return true if it was successfully added
     */
    public boolean addVehicle(Vehicle v) {
       if (this.findVehicle(v.getVIN()) != null)
           return false;
       this.getVehicles().add(v);
       return true;
    }

    /**
     * This method will look for a vehicle which corresponds to the given VIN. If found, it will be removed.
     * @param VIN is the VIN that corresponds to the vehicle to be removed.
     * @return true if the vehicle was found and removed, false otherwise
     */
    public boolean removeVehicle(int VIN) {
        Vehicle vehicle = findVehicle(VIN);
        if (vehicle != null) {
            this.getVehicles().remove(vehicle);
            return true;
        }
        return false;
    }

    /**
     * This function parses through the registered vehicles' VINs within the system and checks if a vehicle with the same VIN can be found.
     * @param VIN is the VIN of the vehicle being searched for.
     * @return null if the vehicle was not found, otherwise return the vehicle that has the same VIN as the checked vehicle.
     */
    public Vehicle findVehicle(int VIN) {
        for (Vehicle vehicle : this.getVehicles()) {
            if (vehicle.getVIN() == VIN) {
                return vehicle;
            }
        }
        return null;
    }

    /**
     * Display all the vehicles. If there are none registered, then an appropriate message will be displayed.
     */
    public void displayVehicles() {
        if (vehicles != null) {
            for (Vehicle vehicle : vehicles)
                System.out.println(vehicle.toString());
        }
        else {
            System.out.println("There are no vehicles registered.");
        }
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }
}
