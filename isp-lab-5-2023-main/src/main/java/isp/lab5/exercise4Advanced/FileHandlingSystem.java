package isp.lab5.exercise4Advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public final class FileHandlingSystem {
    /**
     *
     * All parameters are credentials of a user.
     * The function checks for a file. If it does not exist, it will be created,
     * then the user's credentials will be added to the list
     * @return true only if the user was added successfully
     */
    public boolean addUserCredentials(String filename, String name, String surname, String username, String password) {
        // add info to general user file for login
        // FILE FORMAT: name, surname, username, password
        try {
            File users = new File("src\\" + filename);
            if (!users.exists()) {
                users.createNewFile();
            }
            FileWriter Users = new FileWriter("src\\" + filename);
            Users.write(name+", "+surname+", "+username+", "+password);
            Users.close();
        } catch (IOException e){
            System.err.println("File could not be created or accessed.");
        }
        return true;
    }

    public boolean addPartialCredentials(String filename, String username, String name, String surname) throws Exception {
        // FILE FORMAT: name, surname, username, password
        // remember the info from that line
        String userCredentials = searchLine(filename, username);
        if (userCredentials.compareTo("No line found") == 0) return false;

        String[] data = userCredentials.split(", ");
        ArrayList<String> userData = new ArrayList<>(4);
        Collections.addAll(userData, data);

        // delete line from file
        removeLine(filename, username);

        // add new line
        addUserCredentials(filename, name, surname, username, userData.get(3));

        return true;
    }

    public boolean updateUserCredentials(String filename, String username, ArrayList<String> fieldsToUpdate, ArrayList<String> valuesOfFields) throws Exception {
        // FILE FORMAT: name, surname, username, password
        if (fieldsToUpdate.size() != valuesOfFields.size()) throw new Exception("NUMBER OF FIELDS AND NUMBER OF VALUES SHOULD BE THE SAME.");
        if (fieldsToUpdate.size() > 4) throw new Exception("CANNOT UPDATE MORE THAN 4 FIELDS");
        if (fieldsToUpdate.contains("username")) throw new Exception("CANNOT UPDATE USERNAME.");

        // look for username and save it as a List
        if (searchLine(filename, username).compareTo("No line found") == 0) return false;
        String userCredentials = searchLine(filename, username);
        String[] data = userCredentials.split(", ");
        ArrayList<String> userData = new ArrayList<>(4);
        Collections.addAll(userData, data); // adds all the fields to the string list

        // check and add the new fields
        for (int i = 0; i < fieldsToUpdate.size(); i++) {
            if (chooseField(fieldsToUpdate.get(i)) == -1) throw new Exception("Error accessing specified field.");
            // add the new fields value if all is well
            userData.set(chooseField(fieldsToUpdate.get(i)), valuesOfFields.get(i));
        }

        // remove previous user
        if(!removeLine(filename, username)) throw new Exception("Could not remove user credentials.");

        // add the user back to the file
        addUserCredentials(filename, userData.get(0), userData.get(1), userData.get(2), userData.get(3));

        return true;
    }

    public String searchLine(String filename, String valueToRetrieve) {
        try {
            File users = new File("src\\" + filename);
            Scanner reader = new Scanner(users);
            while (reader.hasNextLine()) {
                if (reader.nextLine().contains(valueToRetrieve))
                    return reader.nextLine();
            }
        } catch (IOException e){
            System.err.println("File could not be accessed.");
        }
        return "No line found";
    }

    /**
     *
     * @param valueToDelete is the line we're looking to remove from the file
     * @return true if the line was successfully removed; false if content was not found
     * Parse the file; if current info is not the content we're looking for, copy it to another file. If they are, skip the line
     * After that, delete old file and rename the current file to the old file's name
     */
    public boolean removeLine(String filename, String valueToDelete) throws Exception {
        // create new file
        try {
            File newUsers = new File("src\\temp.txt");
            if (!newUsers.exists()) newUsers.createNewFile();
        } catch (IOException e1) {
            System.err.println("Temporary file could not be created.");
        }

        // parse old file and copy content (without the user we're looking for)
        try {
            File users = new File("src\\" + filename);
            Scanner reader = new Scanner(users);
            while (reader.hasNextLine()) {
                if (reader.nextLine().contains(valueToDelete)) {
                    // copy content into the new file
                    try {
                        FileWriter newUsersWrite = new FileWriter("src\\temp.txt");
                        newUsersWrite.write(reader.nextLine());
                        newUsersWrite.close();
                    } catch (FileNotFoundException e) {
                        System.err.println("Temporary file could not be accessed.");
                    }
                }
                // otherwise skip the line
            }
            // delete old file
            if(!users.delete()) throw new Exception("Old file could not be deleted.");
        } catch (IOException e){
            System.err.println("File could not be accessed.");
        }

        // rename new file to old file's name
        try {
            new File("src\\temp.txt").renameTo(new File("src\\"+filename));
            return true;
        } catch (Exception e) {
            System.err.println("Could not rename file.");
        }

        return false;
    }

    public int chooseField(String field) {
        return switch (field) {
            case "name" -> 0;
            case "surname" -> 1;
            case "password" -> 3;
            default -> -1;
        };
    }
    public boolean addOrganizer(String filename, String name, String surname, String username, String eventOrganized) {
        // add info to organizer file
        // FILE FORMAT: name, surname, username, eventOrganized
        try {
            File organizers = new File("src\\"+filename);
            if (!organizers.exists()) {
                organizers.createNewFile(); // create file
            } // then write in the file
            FileWriter Organizers = new FileWriter("src\\"+filename);
            Organizers.write(name+", "+surname+", "+username+", "+eventOrganized);
            Organizers.close();
        } catch (IOException e) {
            System.err.println("File could not be created or accessed.");
        }
        return true;
    }

    /**
     *
     * Version of addOrganizer method in which I only add the username and eventOrganized.
     * The rest of the data can be added later down the line
     * @return true only if the organizer's partial credentials were added successfully to the file
     */
    public boolean addOrganizer(String filename, String username, String eventOrganized) {
        // FILE FORMAT: name, surname, username, eventOrganized
        try {
            File organizers = new File("src\\"+filename);
            if (!organizers.exists()) {
                organizers.createNewFile(); // create file
            } // then write in the file
            FileWriter Organizers = new FileWriter("src\\"+filename);
            Organizers.write(username+", "+eventOrganized);
            Organizers.close();
        } catch (IOException e) {
            System.err.println("File could not be created or accessed.");
        }
        return true;
    }

    /**
     *
     * @param date is the date when the event will take place
     * @param totalTickets is the total number of tickets for a given event
     * @param ticketsAvailable is the number of tickets that are still available to buy
     *
     */
    public boolean addEvent(String filename, String eventName, LocalDateTime date, int totalTickets, int ticketsAvailable, double ticketPrice) {
        // FILE FORMAT: eventName, date, totalTickets, ticketPrice, ticketsAvailable
        try {
            File events = new File("src\\"+filename);
            if (!events.exists()) {
                events.createNewFile(); // create file
            } // then write in the file
            FileWriter Events = new FileWriter("src\\"+filename);
            Events.write(eventName+", "+date+", "+totalTickets+", "+ticketPrice+", "+ticketsAvailable);
            Events.close();
        } catch (IOException e) {
            System.err.println("File could not be created or accessed.");
        }
        return true;
    }

    public ArrayList<Event> updateEventList(String filename) {
        File eventsList = new File("src\\" + filename);
        ArrayList<Event> events = new ArrayList<>();
        Scanner reader = null; // initialized with null so that while is valid

        // handle errors from file
        try {
            reader = new Scanner(eventsList);
        } catch(FileNotFoundException e) {
            System.err.println("ERROR: File is empty.");
            System.exit(0);
        }

        // place in events the things read from file
        // FILE FORMAT: eventName, date, totalTickets, ticketPrice, ticketsAvailable
        while (reader.hasNextLine()) {
            String eventInfo = reader.nextLine();
            String[] event = eventInfo.split(", ");
            events.add(new Event(event[0], LocalDateTime.parse(event[1]), Integer.parseInt(event[2]), Double.parseDouble(event[3])));
            events.getLast().setTicketsAvailable(Integer.parseInt(event[4]));
        }
        return events;
    }
    public boolean addClient(String filename, String name, String surname, String username, long ticketCode, TicketStatus ticketStatus, ClientStatus clientStatus) {
        // FILE FORMAT: Name, Surname, Username, TicketCode, TicketStatus, ClientStatus
        try {
            File client = new File("src\\"+filename);
            if (!client.exists()) {
                client.createNewFile(); // create file
            } // then write in the file
            FileWriter Client = new FileWriter("src\\"+filename);
            Client.write(name+", "+surname+", "+username+", "+ticketCode+", "+ticketStatus+", "+clientStatus);
            Client.close();
        } catch (IOException e) {
            System.err.println("File could not be created or accessed.");
        }
        return true;
    }

    /**
     *
     * @param eventOrganized is the event of which we want to get the client list
     * @return the client list of the given event
     */
    public ArrayList<Client> clientListUpdate(String eventOrganized) {
        // open file and read from file
        String filename = "src\\client list " + eventOrganized + ".txt";
        File clientsList = new File(filename);
        ArrayList<Client> clients = new ArrayList<>();
        Scanner reader = null; // initialized with null so that while is valid

        // handle errors from file
        try {
            reader = new Scanner(clientsList);
        } catch(FileNotFoundException e) {
            System.err.println("ERROR: File is empty.");
            System.exit(0);
        }

        // place in clients the things read from file
        // FILE FORMAT: Name, Surname, Username, TicketCode, TicketStatus, ClientStatus
        while (reader.hasNextLine()) {
            String clientInfo = reader.nextLine();
            String[] client = clientInfo.split(", ");
            clients.add(new Client(client[0], client[1], client[2]));
            clients.getLast().getTicket().setTicketCode(Long.parseLong(client[3]));
            clients.getLast().getTicket().setTicketStatus(TicketStatus.valueOf(client[4]));
            clients.getLast().setAttendanceStatus(ClientStatus.valueOf(client[5]));
        }
        return clients;
    }
}
