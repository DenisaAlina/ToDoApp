package se.lexicon.dao;

import se.lexicon.dao.sequencers.TodoItemTaskIdSequencer;
import se.lexicon.model.TodoItemTask;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoItemTaskDAOCollection implements TodoItemTaskDAO{

    private List<TodoItemTask> todoItemTasks = new ArrayList<>();
    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        todoItemTask.setId(TodoItemTaskIdSequencer.nextId());
        todoItemTasks.add(todoItemTask);
        return todoItemTask;
    }

    @Override
    public TodoItemTask findById(int id) {
        return todoItemTasks.stream()
                .filter(todoItemTask -> todoItemTask.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<TodoItemTask> findAll() {
        return new ArrayList<>(todoItemTasks);
    }

    @Override
    public List<TodoItemTask> findByAssignedStatus(boolean status) {
        return todoItemTasks.stream()
                .filter(todoItemTask -> todoItemTask.isAssigned() == status)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoItemTask> findByPersonId(int personId) {
        return todoItemTasks.stream()
                .filter(todoItemTask -> todoItemTask.getAssignee() != null && todoItemTask.getAssignee().getId() == personId)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(TodoItemTask todoItemTask) {
        todoItemTasks.remove(todoItemTask);
    }
}
