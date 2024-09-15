package se.lexicon.dao;

import se.lexicon.model.AppRole;
import se.lexicon.model.AppUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppUserDAOJdbcImpl implements AppUserDAO{
    private Collection<AppUser> appUsers;
    public AppUserDAOJdbcImpl() {
        this.appUsers = new ArrayList<>();
    }
    @Override
    public AppUser persist(AppUser appUser) {
        appUsers.add(appUser);
        return appUser;
    }
    @Override
    public AppUser findByUsername(String username){
        for (AppUser appUser : appUsers) {
            if (appUser.getUsername().equals(username)) {
                return appUser;
            }
        }
        return null;
    }
    @Override
    public Collection<AppUser> findAll() {
        return new ArrayList<>(appUsers);
    }
    @Override
    public void remove(String username) {
        appUsers.removeIf(appUser -> appUser.getUsername().equals(username));
    }
}
