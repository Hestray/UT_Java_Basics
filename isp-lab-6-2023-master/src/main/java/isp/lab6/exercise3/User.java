package isp.lab6.exercise3;

import java.util.Objects;

public class User {
    private String username;
    private String password;
    private boolean loggedInStatus;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.loggedInStatus = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoggedInStatus(boolean loggedInStatus) {
        this.loggedInStatus = loggedInStatus;
    }
}
