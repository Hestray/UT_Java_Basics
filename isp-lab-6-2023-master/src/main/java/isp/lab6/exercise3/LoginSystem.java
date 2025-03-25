package isp.lab6.exercise3;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class LoginSystem {
    private Set<User> users     = new HashSet<>();
    private OnlineStore store   = new OnlineStore();

    public LoginSystem(OnlineStore store) { this.store = store; }

    /**
     * This method will register a new user and add it to the list of users in the login system
     * @param username
     * @param password
     * @return true if the user was successfully registered, false otherwise.
     */
    public boolean register(String username, String password) {
        if (!Objects.equals(username, "") && username != null && password.length() > 0) {
            User user = new User(username, password);
            // users is a set and will take care of making sure there are no two users with the same credentials
            users.add(user);
            user.setLoggedInStatus(true);   // set the logged in status as true = logged in
            store.addSession(username);     // add session on user login
            return true;
        }
        return false; // user was not registered
    }

    /**
     * This method checks if a user with the given credentials can be found. if it can be found, a new active sessions starts
     * and the user is logged in.
     * @param username to log in
     * @param password to log in
     * @return true if user could be found, false otherwise
     */
    public boolean login(String username, String password) {
        User findingNemo = new User(username, password);
        for (User u : users) {
            if (u.equals(findingNemo)) {
                // user was found and can be logged in
                u.setLoggedInStatus(true);  // set the logged in status as true = logged in
                store.addSession(username); // add session on user login
                return true;
            }
        }
        return false;
    }

    /**
     * This method check if the given user is part of the active sessions. If it is, remove the user from the active sessions
     * within the store and log him out.
     * @param username
     * @return true if the user was successfully logged out, false if an error occurs somewhere
     */
    public boolean logout(String username) {
        for (Map.Entry<String, ActiveSession> parseSession : store.getSessions().entrySet()) {
            // for each of the current active sessions within the store
            if (parseSession.getKey().equals(username)) {
                // remove session from active sessions within the store
                store.removeSession(username);
                // now parse through users in order to set the logged in status as false
                for (User u : users) {
                    if (u.getUsername().equals(username)) {
                        u.setLoggedInStatus(false);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public OnlineStore getStore() {
        return store;
    }
}
