package isp.lab6.exercise3;

import java.util.ArrayList;

public class Exercise3 {
    public static void main(String[] args) {
        UserInterface mainInterface = new UserInterface();

        // just so I can have something to work with without adding products every. single. time.
        Product prod3 = new Product("Frozen spinach", 7.5D);
        Product prod1 = new Product("Lamp", 83.75D);
        Product prod2 = new Product("Soft blanket grey", 37D);

        ArrayList<Product> ICryInTesting = new ArrayList<>();
        ICryInTesting.add(prod1);
        ICryInTesting.add(prod2);
        ICryInTesting.add(prod3);

        mainInterface.getStore().addProduct(ICryInTesting);

        // now add the one and only developer
        mainInterface.registerDEVs("DEV::Emma", "DEV::1234");

        // accessing interface
        while (true) {
            mainInterface.loadInterface();
        }
    }
}
