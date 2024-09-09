package se.lexicon.model;

import java.util.Objects;

public class AppUser {
    private int id;
    private String username;
    private String password;
    private AppRole role;

    // Constructor, getters, and setters

    public AppUser(int id, String username, String password, AppRole role) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        this.id=id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public AppRole getRole() {
        return role;
    }

    public void setRole(AppRole role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        this.role = role;
    }

    // Override toString() method
    @Override
    public String toString() {
        return "AppUser{id=" + id +" username='" + username + '\'' + ", role=" + role + '}';
    }

    // Override equals() and hashCode() methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id == appUser.id &&
                Objects.equals(username, appUser.username) &&
                Objects.equals(role, appUser.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, role);
    }
}
