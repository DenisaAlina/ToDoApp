package se.lexicon.dao;

import se.lexicon.model.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.sql.*;

public class PeopleDAOJdbcImp  implements PeopleDAO {
    Connection connection = se.lexicon.dao.MySQLConnection.getConnection();



    @Override
    public Person create(Person person) {
        try {
            String CREATE_PERSON = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PERSON, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                person.setId(resultSet.getInt(1));
            }
            return person;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Person> findAll() {
        List<Person> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");

            while (resultSet.next()) {
                Person person = new Person(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );
                person.setId(resultSet.getInt("person_id"));
                result.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Person findById(int id) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE person_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Person person =  new Person(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );
                person.setId(resultSet.getInt("person_id"));
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Person> findByName(String name) {

        // Seperate the first name and last name
        String[] names = name.split(" ");
        String firstName = "";
        String lastName = "";
        if (names.length < 1) {
            throw new IllegalArgumentException("Name must contain at least a first name");
        }
        else if (names.length < 2) {
            firstName = names[0];
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE first_name = ?");
                preparedStatement.setString(1, firstName);

                ResultSet resultSet = preparedStatement.executeQuery();

                List<Person> result = new ArrayList<>();
                while (resultSet.next()) {
                    Person person = new Person(
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")
                    );
                    person.setId(resultSet.getInt("person_id"));
                    result.add(person);
                }
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            firstName = names[0];
            lastName = names[1];

            try {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE first_name = ? AND last_name = ?");
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);

                ResultSet resultSet = preparedStatement.executeQuery();

                List<Person> result = new ArrayList<>();
                while (resultSet.next()) {
                    Person person = new Person(
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")
                    );
                    person.setId(resultSet.getInt("person_id"));
                    result.add(person);
                }
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    public Person update(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?");
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getId());

            preparedStatement.execute();
            return person;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM person WHERE person_id = ?");
            preparedStatement.setInt(1, id);

            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
