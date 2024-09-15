package se.lexicon.dao;

import se.lexicon.dao.sequencers.AppUserSequencer;
import se.lexicon.model.ToDoItemTask;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoItemTaskDAOJdbcImpl implements TodoItemTaskDAO {
    ArrayList<ToDoItemTask> todoItemTasks;

    public void ToDoItemTaskDAO() {
        todoItemTasks = new ArrayList<>();
    }
    @Override
    public ToDoItemTask persist(ToDoItemTask todoItemTask) {
        if (todoItemTask == null) {
            throw new IllegalArgumentException("TodoItemTask todoItemTask was null");
        }
        todoItemTask.setId(AppUserSequencer.nextId());
        todoItemTasks.add(todoItemTask);
        return todoItemTask;
    }
    @Override
    public ToDoItemTask findById(int todoItemTaskId) {
        if (todoItemTaskId > 0) {
            for (ToDoItemTask todoItemTask : todoItemTasks) {
                if (todoItemTask.getId() == todoItemTaskId) {
                    return todoItemTask;
                }
            }
        }
        return null;
    }
    @Override
    public ArrayList<ToDoItemTask> findAll() {
        return todoItemTasks;
    }
    @Override
    public ArrayList<ToDoItemTask> findByAssignedStatus(boolean isAssigned) {
        ArrayList<ToDoItemTask> assignedStatus = new ArrayList<>();
        for (ToDoItemTask todoItemTask : todoItemTasks) {
            if (todoItemTask.isAssigned() == isAssigned) {
                assignedStatus.add(todoItemTask);
            }
        }
        return assignedStatus;
    }
    public ArrayList<ToDoItemTask> findByPersonId(int personId) {
        if (personId < 0) {
            throw new IllegalArgumentException("PersonId must be not less than 0");
        }
        ArrayList<ToDoItemTask> tasksAssignedToThePerson = new ArrayList<>();
        for (ToDoItemTask toDoItemTask : todoItemTasks) {
            tasksAssignedToThePerson.add(toDoItemTask);
        }
        return tasksAssignedToThePerson;
    }
    @Override
    public void remove(int todoItemTaskId) {
        if (todoItemTaskId < 0) {
            throw new IllegalArgumentException("ToDoItemTaskId must not be less than 0");
        }
        for (ToDoItemTask toDoItemTask : todoItemTasks) {
            if (toDoItemTask.getId() == todoItemTaskId) {
                todoItemTasks.remove(toDoItemTask);
                break;
            }
        }
    }
}
