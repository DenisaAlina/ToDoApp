package se.lexicon.dao;

import se.lexicon.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOJdbcImpl implements PersonDAO{
    private static final String INSERT_PERSON = "INSERT INTO person (first_name, last_name, email, credentials_id) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM person WHERE id = ?";
    private static final String SELECT_BY_EMAIL = "SELECT * FROM person WHERE email = ?";
    private static final String SELECT_ALL = "SELECT * FROM person";
    private static final String DELETE_PERSON = "DELETE FROM person WHERE id = ?";

    @Override
    public Person persist(Person person) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PERSON, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setString(3, person.getEmail());
            statement.setInt(4, person.getCredentials().getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 1) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        person.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person findById(int id) {
        Person person = null;
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                person = new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person findByEmail(String email) {
        Person person = null;
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                person = new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                persons.add(new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        null // Implement loading AppUser
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    @Override
    public void remove(Person person) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PERSON)) {
            statement.setInt(1, person.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
