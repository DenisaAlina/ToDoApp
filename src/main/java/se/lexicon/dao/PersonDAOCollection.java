package se.lexicon.dao;

import se.lexicon.dao.sequencers.PersonIdSequencer;
import se.lexicon.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDAOCollection implements PersonDAO{

    private List<Person> persons = new ArrayList<>();
    @Override
    public Person persist(Person person) {
        person.setId(PersonIdSequencer.nextId());
        persons.add(person);
        return person;
    }

    @Override
    public Person findById(int id) {
        return persons.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Person findByEmail(String email) {
        return persons.stream()
                .filter(person -> person.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(persons);
    }

    @Override
    public void remove(Person person) {
        persons.remove(person);
    }
}
