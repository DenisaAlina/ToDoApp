package se.lexicon.dao;

import se.lexicon.model.AppRole;
import se.lexicon.model.AppUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppUserDAOJdbcImpl implements AppUserDAO{
    private static final String INSERT_APPUSER = "INSERT INTO app_user (username, password, role) VALUES (?, ?, ?)";
    private static final String SELECT_BY_USERNAME = "SELECT * FROM app_user WHERE username = ?";
    private static final String SELECT_ALL = "SELECT * FROM app_user";
    private static final String DELETE_APPUSER = "DELETE FROM app_user WHERE username = ?";

    @Override
    public AppUser persist(AppUser appUser) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_APPUSER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, appUser.getUsername());
            statement.setString(2, appUser.getPassword());
            statement.setString(3, appUser.getRole().name());

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    appUser.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appUser;
    }

    @Override
    public AppUser findByUsername(String username) {
        AppUser appUser = null;
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_USERNAME)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                appUser = new AppUser(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        AppRole.valueOf(resultSet.getString("role"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appUser;
    }

    @Override
    public List<AppUser> findAll() {
        List<AppUser> appUsers = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                AppUser appUser = new AppUser(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        AppRole.valueOf(resultSet.getString("role"))
                );
                appUsers.add(appUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appUsers;
    }

    @Override
    public void remove(AppUser appUser) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_APPUSER)) {
            statement.setString(1, appUser.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
