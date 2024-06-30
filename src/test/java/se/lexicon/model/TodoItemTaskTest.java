package se.lexicon.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class TodoItemTaskTest {
    @Test
    public void testTodoItemTaskCreation() {
        Person creator = new Person(1, "John", "Doe", "john.doe@example.com");
        TodoItem item = new TodoItem(1, "Title", "Description", LocalDate.now().plusDays(1), creator);
        Person assignee = new Person(2, "Jane", "Smith", "jane.smith@example.com");
        TodoItemTask task = new TodoItemTask(1, item, assignee);
        assertEquals(1, task.getId());
        assertEquals(item, task.getTodoItem());
        assertEquals(assignee, task.getAssignee());
        assertTrue(task.isAssigned());
    }

    @Test
    public void testTodoItemTaskCreationWithoutAssignee() {
        Person creator = new Person(1, "John", "Doe", "john.doe@example.com");
        TodoItem item = new TodoItem(1, "Title", "Description", LocalDate.now().plusDays(1), creator);
        TodoItemTask task = new TodoItemTask(1, item, null);
        assertEquals(1, task.getId());
        assertEquals(item, task.getTodoItem());
        assertNull(task.getAssignee());
        assertFalse(task.isAssigned());
    }

    @Test
    public void testSetAssignee() {
        Person creator = new Person(1, "John", "Doe", "john.doe@example.com");
        TodoItem item = new TodoItem(1, "Title", "Description", LocalDate.now().plusDays(1), creator);
        TodoItemTask task = new TodoItemTask(1, item, null);
        Person assignee = new Person(2, "Jane", "Smith", "jane.smith@example.com");
        task.setAssignee(assignee);
        assertEquals(assignee, task.getAssignee());
        assertTrue(task.isAssigned());
    }

    @Test
    public void testSetAssigneeToNull() {
        Person creator = new Person(1, "John", "Doe", "john.doe@example.com");
        TodoItem item = new TodoItem(1, "Title", "Description", LocalDate.now().plusDays(1), creator);
        Person assignee = new Person(2, "Jane", "Smith", "jane.smith@example.com");
        TodoItemTask task = new TodoItemTask(1, item, assignee);
        task.setAssignee(null);
        assertNull(task.getAssignee());
        assertFalse(task.isAssigned());
    }

    @Test
    public void testSetTodoItem() {
        Person creator = new Person(1, "John", "Doe", "john.doe@example.com");
        TodoItem item1 = new TodoItem(1, "Title1", "Description1", LocalDate.now().plusDays(1), creator);
        TodoItemTask task = new TodoItemTask(1, item1, null);
        TodoItem item2 = new TodoItem(2, "Title2", "Description2", LocalDate.now().plusDays(2), creator);
        task.setTodoItem(item2);
        assertEquals(item2, task.getTodoItem());
    }

    @Test
    public void testSetTodoItemToNull() {
        Person creator = new Person(1, "John", "Doe", "john.doe@example.com");
        TodoItem item = new TodoItem(1, "Title", "Description", LocalDate.now().plusDays(1), creator);
        TodoItemTask task = new TodoItemTask(1, item, null);
        assertThrows(IllegalArgumentException.class, () -> {
            task.setTodoItem(null);
        });
    }

    @Test
    public void testGetSummary() {
        Person creator = new Person(1, "John", "Doe", "john.doe@example.com");
        TodoItem item = new TodoItem(1, "Title", "Description", LocalDate.now().plusDays(1), creator);
        Person assignee = new Person(2, "Jane", "Smith", "jane.smith@example.com");
        TodoItemTask task = new TodoItemTask(1, item, assignee);
        String expected = String.format("{id: 1, assigned: true, todoItem: %s, assignee: %s}", item.getSummary(), assignee.getSummary());
        assertEquals(expected, task.getSummary());
    }
}
