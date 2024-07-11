package se.lexicon.dao;

import se.lexicon.model.AppUser;

import java.util.ArrayList;
import java.util.List;

public class AppUserDAOCollection implements AppUserDAO {

    List<AppUser> appUserStorage = new ArrayList<>(); // [appUser1, appUser]

    @Override
    public AppUser persist(AppUser appUser) {
        // todo: how to implement it
       // appUser.setId(2);
        appUserStorage.add(appUser);
        return appUser;
    }

    @Override
    public AppUser findByUsername(String username) {
        return appUserStorage.stream()
                .filter(appUser -> appUser.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<AppUser> findAll() {
        return new ArrayList<>(appUserStorage);
    }

    @Override
    public void remove(AppUser appUser) {
        appUserStorage.remove(appUser);
    }





}
