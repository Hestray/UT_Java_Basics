package TestExercise2;

import isp.lab6.exercise2.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestExercise2 {
    RegistrySystem vehicleRegistry;
    @Before
    public void initializationPhase() {
        vehicleRegistry = new RegistrySystem();
        // add a few Vehicles
        Vehicle v1 = new Vehicle(1, "ABC123", "Toyota", "Camry", 2020);
        Vehicle v2 = new Vehicle(2, "XYZ789", "Honda", "Civic", 2018);
        Vehicle v3 = new Vehicle(3, "LMN456", "Ford", "Mustang", 2021);
        vehicleRegistry.addVehicle(v1);
        vehicleRegistry.addVehicle(v2);
        vehicleRegistry.addVehicle(v3);
    }

    @Test public void testAddingVehiclesWithIdenticalVIN() {
        Vehicle v4 = new Vehicle(2, "AAA000", "Hyundai", "i20", 2008);
        Assert.assertFalse(vehicleRegistry.addVehicle(v4));
    }

    @Test public void testRemovingAnInexistentVehicle() {
        Assert.assertFalse(vehicleRegistry.removeVehicle(5));
    }
}
