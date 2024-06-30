package se.lexicon.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
public class TodoItemTest {
    @Test
    public void testTodoItemCreation() {
        Person creator = new Person(1, "John", "Doe", "john.doe@example.com");
        TodoItem item = new TodoItem(1, "Title", "Description", LocalDate.now().plusDays(1), creator);
        assertEquals(1, item.getId());
        assertEquals("Title", item.getTitle());
        assertEquals("Description", item.getDescription());
        assertEquals(LocalDate.now().plusDays(1), item.getDeadline());
        assertEquals(creator, item.getCreator());
    }

    @Test
    public void testIsOverdue() {
        Person creator = new Person(1, "John", "Doe", "john.doe@example.com");
        TodoItem item = new TodoItem(1, "Title", "Description", LocalDate.now().minusDays(1), creator);
        assertTrue(item.isOverdue());
    }

    @Test
    public void testIsNotOverdue() {
        Person creator = new Person(1, "John", "Doe", "john.doe@example.com");
        TodoItem item = new TodoItem(1, "Title", "Description", LocalDate.now().plusDays(1), creator);
        assertFalse(item.isOverdue());
    }

    @Test
    public void testSetTitle() {
        Person creator = new Person(1, "John", "Doe", "john.doe@example.com");
        TodoItem item = new TodoItem(1, "Title", "Description", LocalDate.now().plusDays(1), creator);
        item.setTitle("New Title");
        assertEquals("New Title", item.getTitle());
    }

    @Test
    public void testSetTitleToNull() {
        Person creator = new Person(1, "John", "Doe", "john.doe@example.com");
        TodoItem item = new TodoItem(1, "Title", "Description", LocalDate.now().plusDays(1), creator);
        assertThrows(IllegalArgumentException.class, () -> {
            item.setTitle(null);
        });
    }

    @Test
    public void testSetTitleToEmpty() {
        Person creator = new Person(1, "John", "Doe", "john.doe@example.com");
        TodoItem item = new TodoItem(1, "Title", "Description", LocalDate.now().plusDays(1), creator);
        assertThrows(IllegalArgumentException.class, () -> {
            item.setTitle("");
        });
    }

    @Test
    public void testSetDeadline() {
        Person creator = new Person(1, "John", "Doe", "john.doe@example.com");
        TodoItem item = new TodoItem(1, "Title", "Description", LocalDate.now().plusDays(1), creator);
        item.setDeadline(LocalDate.now().plusDays(2));
        assertEquals(LocalDate.now().plusDays(2), item.getDeadline());
    }

    @Test
    public void testSetDeadlineToNull() {
        Person creator = new Person(1, "John", "Doe", "john.doe@example.com");
        TodoItem item = new TodoItem(1, "Title", "Description", LocalDate.now().plusDays(1), creator);
        assertThrows(IllegalArgumentException.class, () -> {
            item.setDeadline(null);
        });
    }
}
