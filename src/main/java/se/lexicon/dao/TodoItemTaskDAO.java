package se.lexicon.dao;

import se.lexicon.model.ToDoItemTask;


import java.util.ArrayList;
import java.util.List;

public interface TodoItemTaskDAO {
    ToDoItemTask persist(ToDoItemTask todoItemTask);
    ToDoItemTask findById(int todoItemTaskId);
    ArrayList<ToDoItemTask> findAll();
    ArrayList<ToDoItemTask> findByAssignedStatus(boolean done);
    ArrayList<ToDoItemTask> findByPersonId(int personId);
    void remove(int todoItemTaskId);
}
