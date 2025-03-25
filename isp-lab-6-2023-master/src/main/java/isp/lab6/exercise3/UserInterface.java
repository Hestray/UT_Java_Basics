package isp.lab6.exercise3;

import javax.print.attribute.standard.NumberOfInterveningJobs;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private OnlineStore store     = new OnlineStore();
    private LoginSystem loginSyst = new LoginSystem(store);
    private boolean     firstLoad = false; // when it loads for the first time
    private boolean     advance   = false; // used to check if there is an active user atm
    private String      username  = "";
    private String      password  = "";

    private void displayProducts(List<Product> products) {
        System.out.println("The store offers the following products:");
        System.out.println("NAME\t\t\t\tPRICE");
        for (Product p : products) {
            System.out.println(p.getName() + "\t\t\t\t" + p.getPrice());
        }
    }

    /**
     * Note that this method delays the menu redirection by a few seconds.
     * @param time in seconds
     */
    private void redirectingDelay(int time) {
        System.out.println("Redirecting");
        for (; time > 0; time--) {
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                System.out.println("Whoops, something went wrong, try again later!");
                System.exit(0);
            }
            System.out.println("\u001B[36m" + time + "..." + "\u001B[0m");
        }
    }

    public boolean registerDEVs(String username, String password) {
        if (loginSyst.register(username, password)) {
            loginSyst.logout(username); // log them out, because registering them also allows them to be online
            return true;
        }
        return false;
    }

    public void loadInterface() {

        if (!firstLoad) {
            System.out.println("This will take a bit to load, stay here with us!");
            redirectingDelay(5);
            firstLoad   = true;
        }

        // check if the store is empty and just end the app if so
        if (store.getProducts().isEmpty()) {
            System.out.println("Sorry, we don't have any items listed for now. Please come back later!");
            System.exit(0);
        }

        Scanner scanner      = new Scanner(System.in);
        double  productPrice = 0;
        boolean found        = false; // used to check if a product was found within the list
        String  choice, choiceWithinMenu, productName = "";

        // login/register
        if (!advance) {
            // only print this menu if advance == false
            System.out.println("Hello and welcome! Please login or register. Type 1 to login, 2 to register a new account");
            System.out.println("In order to stop and exit the menu altogether, type 'quit'.");
            choice = scanner.nextLine();
            while (!advance) {
                // go through this until a valid choice and info has been provided
                switch (choice) {
                    case "1" -> {
                        System.out.println("Username: ");
                        username = scanner.nextLine();
                        System.out.println("Password: ");
                        password = scanner.nextLine();
                        if (loginSyst.login(username, password)) {
                            System.out.println("You have successfully logged in!");
                            advance = true;
                        } else {
                            System.out.println("An error has occurred. Please try again.");
                            System.out.println("Make sure you have an existing account before logging in!");
                            System.out.println("Type 1 to login, 2 to register a new account");
                            choice = scanner.nextLine();
                        }
                    }
                    case "2" -> {
                        System.out.println("Username: ");
                        username = scanner.nextLine();
                        System.out.println("Password: ");
                        password = scanner.nextLine();
                        if (loginSyst.register(username, password)) {
                            System.out.println("You have successfully registered your account!");
                            advance = true;
                        } else {
                            System.out.println("An error has occurred. Please try again.");
                            System.out.println("Make sure that your username is unique!");
                            System.out.println("Type 1 to login, 2 to register a new account");
                            choice = scanner.nextLine();
                        }
                    }

                    case "quit" -> {
                        System.exit(0);
                    }

                    default -> {
                        System.out.println("Invalid choice. Please make sure you choose 1 or 2.");
                        choice = scanner.nextLine();
                    }
                }
            }
        }

        if (username.equals("DEV::Emma") && password.equals("DEV::1234")) {
            // this is the menu meant to be used by the admin
            System.out.println("Welcome, admin! What would you like to do today?");
            System.out.println("1.   Browse the products");
            System.out.println("2.   Add a product or a list of products");
            System.out.println("3.   Log out");
            System.out.println("404. Exit");

            choice = scanner.nextLine();

            switch(choice) {
                case "1" -> {
                    // menu
                    System.out.println("Would you like to sort them? Type:");
                    System.out.println("1.   Sort alphabetically");
                    System.out.println("2.   Sort by price (ascending)");
                    System.out.println("3.   Don't sort");
                    System.out.println("0.   Go back to the previous menu");
                    choiceWithinMenu = scanner.nextLine();
                    if (!choiceWithinMenu.equals("0")) {
                    // depending on choice, display the list of products as requested
                        switch(choiceWithinMenu) {
                            case "1" -> {
                                displayProducts(store.getProductsSorted(Comparator.comparing(Product::getName)));
                            }
                            case "2" -> {
                                displayProducts(store.getProductsSorted(Comparator.comparing(Product::getPrice)));
                            }
                            case "3" -> {
                                displayProducts(store.getProducts());
                            }
                            default -> {
                                System.out.println("Invalid choice. Please try again.");
                            }
                        }
                    }
                }

                case "2" -> {
                    // adding a product or multiple products
                    System.out.println("Please write the NAME and then the PRICE of the product. Type 'quit' in order to stop");
                    System.out.println("\u001B[31m" + "DISCLAIMER: If you don't specifically type quit, it will keep adding products." + "\u001B[0m");
                    System.out.println("\u001B[31m" + "DISCLAIMER: If the price you set is 0, the item is invalid. " +
                            "If the name is empty, the item is also invalid." + "\u001B[0m");

                    while (true) {
                        System.out.println("PRODUCT NAME:\t");
                        productName  = scanner.nextLine();
                        if (productName.equalsIgnoreCase("quit")) break; // escape the loop
                        if (productName.isEmpty()) {
                            System.out.println("Product discarded.");
                        } else {
                            System.out.println("PRODUCT PRICE:\t");
                            productPrice = scanner.nextDouble();
                            scanner.nextLine();  // consume the newline character left by nextDouble()
                            if (productPrice == 0) {
                                System.out.println("Product discarded.");
                            }
                            else {
                                // add the products to the store
                                store.addProduct(new Product(productName, productPrice));

                                // check if admin wants to continue adding
                                System.out.println("Type 'quit' to stop adding products.");
                            }
                        }
                    }
                }

                case "3" -> {
                    if (loginSyst.logout(username)) {
                        System.out.println("Logged out successfully. Redirecting to the login menu...");
                        advance  = false;        // reset this value so that we can log in another user using the same context menu
                        username = ""; password = ""; // empty these two values too
                        redirectingDelay(3);
                    } else {
                        // error can occur only if user is not found in the active session instance
                        System.out.println("Whoops. An error occurred somewhere. Are you sure you have an account registered with us?");
                        System.out.println("Please try again. If it doesn't work, please contact support.");
                    }
                }

                case "404" -> {
                    System.out.println("Thank you for stopping by!");
                    System.out.println("Shop closing...");
                    System.exit(0);
                }

                default -> {
                    System.out.println("Something went wrong. Please make sure you type in a valid choice.");
                    redirectingDelay(3);
                }
            }
        }
        else {
            System.out.println("Welcome to the store! What would you like to do today?");
            System.out.println("1.   Browse the products");
            System.out.println("2.   Buy a product");
            System.out.println("3.   Check out");
            System.out.println("4.   Log out");
            System.out.println("404. Exit");

            choice = scanner.nextLine();

            switch(choice) {
                case "1" -> {
                    // menu
                    System.out.println("Would you like to sort them? Type:");
                    System.out.println("1. Sort alphabetically");
                    System.out.println("2. Sort by price (ascending)");
                    System.out.println("3. Don't sort");
                    System.out.println("0. Go back to the previous menu");
                    choiceWithinMenu = scanner.nextLine();

                    // depending on choice, display the list of products as requested
                    if (!choiceWithinMenu.equals("0")) {
                        switch (choiceWithinMenu) {
                            case "1" -> {
                                displayProducts(store.getProductsSorted(Comparator.comparing(Product::getName)));
                            }
                            case "2" -> {
                                displayProducts(store.getProductsSorted(Comparator.comparing(Product::getPrice)));
                            }
                            case "3" -> {
                                displayProducts(store.getProducts());
                            }
                            default -> {
                                System.out.println("Invalid choice. Please try again.");
                            }
                        }
                    }
                }

                case "2" -> {
                    // request data in order to add it to cart
                    System.out.println("What product would you like to buy?\t");
                    productName  = scanner.nextLine();
                    System.out.println("What quantity would you like to buy?\t");
                    productPrice = scanner.nextDouble(); // reusing the variable

                    // find the product in the list of products available in the shop
                    for (Product p : store.getProducts()) {
                        if (p.getName().equalsIgnoreCase(productName)) {
                            // add to cart
                            store.addToCart(username, p, productPrice);
                            found = true;
                            break; // set to last to escape the for loop
                        }
                    }
                    if (!found) {
                        // if the item wasn't found
                        System.out.println("Whoops, an error occurred. Please try again. If it doesn't work, contact support.");
                        System.out.println("Make sure that you wrote the right name");
                    }
                    found = false; // reset it
                }

                case "3" -> {
                    System.out.println("Your total is: " + store.checkout(username));
                    System.out.println("I won't take your money... \033[3mfor today\033[0m");
                }

                case "4" -> {
                    // logout
                    if (loginSyst.logout(username)) {
                        System.out.println("Logged out successfully. Redirecting to the login menu...");
                        advance  = false;        // reset this value so that we can log in another user using the same context menu
                        username = ""; password = ""; // empty these two values too
                        redirectingDelay(3);
                    } else {
                        // error can occur only if user is not found in the active session instance
                        System.out.println("Whoops. An error occurred somewhere. Are you sure you have an account registered with us?");
                        System.out.println("Please try again. If it doesn't work, please contact support.");
                    }
                }

                case "404" -> {
                    System.out.println("Thank you for stopping by!");
                    System.out.println("Shop closing...");
                    System.exit(0);
                }

                default -> {
                    System.out.println("Something went wrong. Please make sure you type in a valid choice.");
                    redirectingDelay(3);
                }
            }
        }
    }

    public OnlineStore getStore() {
        return store;
    }
}
