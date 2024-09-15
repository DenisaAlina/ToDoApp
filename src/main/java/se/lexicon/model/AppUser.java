package se.lexicon.model;

import java.util.Objects;

public class AppUser {

    private String username;
    private String password;


    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;
    }





    // Override toString() method
    @Override
    public String toString() {
        return "AppUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    // Override equals() and hashCode() methods
    @Override
    public boolean equals(Object obj) {
        return false;
    }
    public int hashCode() {
        return 0;
    }
}
