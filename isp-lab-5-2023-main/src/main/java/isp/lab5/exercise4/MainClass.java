package isp.lab5.exercise4;

import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainClass {
    static ArrayList<Client> clients = new ArrayList<>();
    static ArrayList<Organizer> organizers = new ArrayList<>();

    public static void ticketHandlingMenu(Organizer organizer) {
        while (true) {
            System.out.println("1. View your ticket\n2. Scan ticket\n3. Check in participants\n4. Go back to organizer menu");
            System.out.println("What would you like to do? ");
            Scanner reader = new Scanner(System.in);
            String Choice = reader.nextLine();

            switch (Choice.toLowerCase()) {
                case "view", "1", "view ticket" -> {
                    System.out.println(organizer.viewTicket());
                }
                case "scan", "2", "scan ticket" -> {
                    if (!organizer.scanTicket()) {
                        System.out.println("Something went wrong or there are no valid tickets to scan. Going back...");
                        organizerMenu(organizer);
                    }
                    else {
                        System.out.println("You have successfully scanned participants' tickets.");
                    }
                }
                case "check", "check in", "3", "check in participants" -> {
                    if (!organizer.checkIn()) {
                        System.out.println("Something went wrong or there are no clients with all tickets scanned.");
                        organizerMenu(organizer);
                    }
                    else {
                        System.out.println("List of clients and their status: ");
                        for (int i = 0; i < MainClass.clients.size(); i++) {
                            System.out.println(i + ". " + clients.get(i).getUsername() + "\t" + clients.get(i).getClientStatus());
                        }
                    }
                }
                case "go back", "back", "menu", "organizer menu", "4" -> {
                    organizerMenu(organizer);
                }
                default -> {
                    System.out.println("Invalid choice.");
                }
            }
            Choice = reader.nextLine();
        }
    }

    public static void organizerMenu(Organizer organizer) {
        while (true) {
            System.out.println("Welcome! These are your options: ");
            System.out.println("1. CREATE EVENT\n2. HANDLE TICKETS AND CLIENTS\n3. LOG OUT");
            System.out.println("What would you like to do? ");
            Scanner reader = new Scanner(System.in);
            String Choice = reader.nextLine();

            switch(Choice.toLowerCase()) {
                case "create", "create event", "1", "1. create event", "1 create event" -> {
                    System.out.println("Please input the following data: ");
                    System.out.println("Number of total tickets: ");
                    int ticketNumber = Integer.parseInt(String.valueOf(reader.nextLine()));
                    System.out.println("Price per ticket: ");
                    double ticketPrice = Double.parseDouble(String.valueOf(reader.nextLine()));

                    // set the values
                    Ticket ticket = new Ticket();
                    ticket.setTicketPrice(ticketPrice);         // reach static attributes
                    ticket.setAvailableTickets(ticketNumber);
                }
                case "tickets", "handle tickets", "2", "2. handle tickets", "2 handle tickets", "tickets and clients", "tc" -> {
                    ticketHandlingMenu(organizer);
                }
                case "logout", "log out", "3" -> { main(new String[]{"logout"}); }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public static void clientMenu(Client client) {
        String Choice;

        while (true) {
            System.out.println("Welcome! These are your options: ");
            System.out.println("1. BUY TICKET\n2. VIEW TICKET\n3. LOG OUT");
            System.out.println("What would you like to do?");
            Scanner reader = new Scanner(System.in);
            Choice = reader.nextLine();

            switch(Choice.toLowerCase()) {
                case "buy", "buy ticket", "1", "1. buy ticket", "1 buy ticket" -> {
                    System.out.println("How many tickets would you like to buy?");
                    Scanner newReader = new Scanner(System.in);
                    int ticketNum = newReader.nextInt();
                    Ticket ticket = new Ticket();

                    // create TicketManager for client
                    TicketManager ticketManager = new TicketManager(ticket.getAvailableTickets());
                    client.setTicketManager(ticketManager);

                    if (!client.acquireTicket(ticketNum, ticket.getTicketPrice())) {
                        System.out.println("There was an error processing your order. Going back...");
                        clientMenu(client);
                    }

                    System.out.println("Please input your credentials: ");
                    String cardNumber;
                    String surname;
                    String name;
                    String CVV;
                    do {
                        System.out.println("Name: ");
                        name = newReader.next();
                        System.out.println("Surname: ");
                        surname = newReader.next();
                        System.out.println("Card number: ");
                        cardNumber = newReader.next();
                        System.out.println("CVV: ");
                        CVV = newReader.next();
                    } while (!client.credentials(name, surname, cardNumber, CVV));

                    System.out.println("Payment went successfully!");
                }
                case "view", "view ticket", "2", "2. view ticket", "2 view ticket" -> {
                    System.out.println(client.viewTicket());
                }
                case "logout", "log out", "3" -> { main(new String[]{"logout"}); }
                default -> System.out.println("Invalid choice, please choose from the two options.");
            }
        }
    }
    public static void signupMenu(String objectType) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Username: ");
        String username = reader.nextLine();
        System.out.println("Password: ");
        String password = reader.nextLine();

        // create object based on type
        switch(objectType) {
            case "Client" -> {
                Client client = new Client(username, password);
                MainClass.clients.add(client);
                clientMenu(client);
            }
            case "Organizer" -> {
                Organizer organizer = new Organizer(username, password, MainClass.clients);
                MainClass.organizers.add(organizer);
                organizerMenu(organizer);
            }
        }
    }

    public static void account(String objectType) {
        String Choice;
        System.out.println("1. SIGN UP\n2. LOG IN");
        Scanner reader = new Scanner(System.in);
        Choice = reader.nextLine();

        switch(Choice.toLowerCase()) {
            case "signup", "1", "sign up", "sign" -> {
                signupMenu(objectType);
            }
            case "login", "2", "log in", "log" -> {
                boolean loggedin = false;

                System.out.println("LOGIN CREDENTIALS");
                System.out.println("Username: ");
                String username = reader.nextLine();
                System.out.println("Password: ");
                String password  = reader.nextLine();

                switch (objectType) {
                    case "Client" -> {
                        for (Client c : MainClass.clients) {
                            if (c.login(username, password)) {
                                loggedin = true;
                                System.out.println("Successfully logged in!");
                                clientMenu(c);
                            }
                        }
                    }
                    case "Organizer" -> {
                        for (Organizer o : MainClass.organizers) {
                            if (o.login(username, password)) {
                                loggedin = true;
                                System.out.println("Successfully logged in!");
                                organizerMenu(o);
                            }
                        }
                    }
                }

                if (!loggedin) {
                    System.out.println("Account could not be found. You should create an account first.");
                    generalMenu();
                }
            }
            default-> System.out.println("Invalid choice");
        }
    }
    public static void generalMenu() {
        String Choice;
        System.out.println("Welcome! Are you a client or an organizer? ");
        Scanner reader = new Scanner(System.in);
        Choice = reader.nextLine();

        while (true) {
            switch(Choice.toLowerCase()) {
                case "client" -> account("Client");
                case "organizer", "organiser" -> account("Organizer");
                default -> System.out.println("You cannot be anything else beside these two.");
            }
            generalMenu();
        }
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String choice = "Enter";
        while (choice.compareTo("exit") != 0) {
            System.out.println("Type exit in order to exit the menu. Otherwise, type go.");
            choice = reader.nextLine();
            if (choice.compareTo("exit") != 0) generalMenu();
        }
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
