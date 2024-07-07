package se.lexicon.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class PersonTest {
    @Test
    public void testPersonCreation() {
        Person person = new Person(1, "John", "Doe", "john.doe@example.com");
        assertEquals(1, person.getId());
        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals("john.doe@example.com", person.getEmail());
    }



    @Test
    public void testNullFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Person(1, null, "Doe", "john.doe@example.com");
        });
    }

    @Test
    public void testNullLastName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Person(1, "John", null, "john.doe@example.com");
        });
    }

    @Test
    public void testNullEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Person(1, "John", "Doe", null);
        });
    }

    @Test
    public void testSetFirstName() {
        Person person = new Person(1, "John", "Doe", "john.doe@example.com");
        person.setFirstName("Jane");
        assertEquals("Jane", person.getFirstName());
    }

    @Test
    public void testSetLastName() {
        Person person = new Person(1, "John", "Doe", "john.doe@example.com");
        person.setLastName("Smith");
        assertEquals("Smith", person.getLastName());
    }

    @Test
    public void testSetEmail() {
        Person person = new Person(1, "John", "Doe", "john.doe@example.com");
        person.setEmail("jane.smith@example.com");
        assertEquals("jane.smith@example.com", person.getEmail());
    }

    @Test
    public void testSetFirstNameToNull() {
        Person person = new Person(1, "John", "Doe", "john.doe@example.com");
        assertThrows(IllegalArgumentException.class, () -> {
            person.setFirstName(null);
        });
    }

    @Test
    public void testSetLastNameToNull() {
        Person person = new Person(1, "John", "Doe", "john.doe@example.com");
        assertThrows(IllegalArgumentException.class, () -> {
            person.setLastName(null);
        });
    }

    @Test
    public void testSetEmailToNull() {
        Person person = new Person(1, "John", "Doe", "john.doe@example.com");
        assertThrows(IllegalArgumentException.class, () -> {
            person.setEmail(null);
        });
    }
}
