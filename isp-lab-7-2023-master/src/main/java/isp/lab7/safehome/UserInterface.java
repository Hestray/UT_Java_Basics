package isp.lab7.safehome;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {
    // this represents the console based menu
    private static final Scanner scanner = new Scanner(System.in);
    private static DoorLockController lockController;
    private String choice;
    private String choiceWithinMenu;
    private String PIN;

    UserInterface(Map<Tenant, AccessKey> validAccess) {
        lockController = new DoorLockController(validAccess);
    }

    public void menu() {
        System.out.println("Select:\n1. Tenant\n2. Admin\n0. Exit");
        this.choice = scanner.nextLine();
        switch (this.choice) {
            case "0" -> {
                System.out.println("Thank you for using this program.");
                System.exit(0);
            }
            case "1" -> {
                // Tenant menu
                System.out.println("Select:\n1. Enter PIN\n0. Go back");
                choiceWithinMenu = scanner.nextLine();
                while (true) {
                    switch (choiceWithinMenu) {
                        case "0" -> // return to the main menu
                            this.menu();
                        case "1" -> {
                            System.out.println("PIN: ");
                            this.PIN = scanner.nextLine();
                            try {
                                DoorStatus status = lockController.enterPin(PIN);
                                System.out.println("Door status: " + status);
                            } catch (InvalidPinException err) {
                                System.out.println("Door is still " + lockController.getDoor().getStatus());
                            } catch (TooManyAttemptsException err) {
                                System.out.println("Contact an administrator.");
                            }
                        }
                        default -> {
                            System.out.println("Invalid choice. Please try again");
                        }
                    }
                    System.out.println("Select:\n1. Enter PIN\n0. Go back");
                    choiceWithinMenu = scanner.nextLine();
                }
            }
            case "2" -> {
                System.out.println("Select:\n1. Add tenant\n2. Remove tenant\n3. View Access Logs\n0. Go back");
                choiceWithinMenu = scanner.nextLine();
                while (true) {
                    switch (choiceWithinMenu) {
                        case "0" -> // return to the main menu
                            this.menu();
                        case "1" -> {
                            String tenantName = "";
                            System.out.println("PIN: ");
                            PIN = scanner.nextLine();
                            System.out.println("Tenant name: ");
                            tenantName = scanner.nextLine();
                            try {
                                lockController.addTenant(PIN, tenantName);  // throws exception
                            } catch (TenantAlreadyExistsException e) {
                                System.out.println("Make sure you add a new tenant.");
                            }
                        }
                        case "2" -> {
                            String tenantName = "";
                            System.out.println("Tenant to remove: ");
                            tenantName = scanner.nextLine();
                            try {
                                lockController.removeTenant(tenantName);    // throws exception
                            } catch (TenantNotFoundException e) {
                                System.out.println("Make sure tenant exists before removing them.");      // confirmation
                            }
                        }
                        case "3" -> {
                            if (lockController.getAccessLogs().isEmpty()) {
                                System.out.println("There are no logs registered.");
                            } else {
                                for (AccessLog log : lockController.getAccessLogs()) {
                                    System.out.println(
                                            "DATE: " + log.getDateTime() + " ### " +
                                                    "TENANT: " + log.getTenantName() + " ### " +
                                                    "OPERATION: " + log.getOperation() + " ### " +
                                                    "ERROR MESSAGE: " + log.getErrorMessage() + " ### " +
                                                    "DOOR STATUS: " + log.getDoorStatus());
                                }
                            }
                        }
                        default -> {
                            System.out.println("Invalid choice. Please choose again");
                        }
                    }
                    System.out.println("Select:\n1. Add tenant\n2. Remove tenant\n3. View Access Logs\n0. Go back");
                    choiceWithinMenu = scanner.nextLine();
                }
            }
        }
    }
}
