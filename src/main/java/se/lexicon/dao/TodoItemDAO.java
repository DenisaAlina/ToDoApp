package se.lexicon.dao;

import se.lexicon.model.Person;
import se.lexicon.model.ToDoItem;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface TodoItemDAO {

    ToDoItem create(ToDoItem todoItem);
    Collection<ToDoItem> findAll();
    ToDoItem findById(int todoItemId);
    Collection<ToDoItem> findByDoneStatus(boolean done);
    Collection<ToDoItem> findByAssignee(int assigneeId);
    Collection<ToDoItem> findByAssignee(Person assignee);
    Collection<ToDoItem> findByUnassignedTodoItems();
    ToDoItem update (ToDoItem todoItem);
    boolean deleteById(int todoItemId);


}
