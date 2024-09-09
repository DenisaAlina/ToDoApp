package se.lexicon.dao;

import se.lexicon.model.TodoItemTask;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoItemTaskDAOJdbcImpl implements TodoItemTaskDAO {
    private static final String INSERT_TASK = "INSERT INTO todo_item_task (todo_item_id, assignee_id, assigned) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM todo_item_task WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM todo_item_task";
    private static final String DELETE_TASK = "DELETE FROM todo_item_task WHERE id = ?";

    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_TASK, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, todoItemTask.getTodoItem().getId());
            statement.setInt(2, todoItemTask.getAssignee() != null ? todoItemTask.getAssignee().getId() : 0);
            statement.setBoolean(3, todoItemTask.isAssigned());

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    todoItemTask.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItemTask;
    }

    @Override
    public TodoItemTask findById(int id) {
        TodoItemTask todoItemTask = null;
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                todoItemTask = new TodoItemTask(
                        resultSet.getInt("id"),
                        null, // Implement logic to load TodoItem
                        null // Implement logic to load Person (assignee)
                );
                todoItemTask.setAssigned(resultSet.getBoolean("assigned"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItemTask;
    }

    @Override
    public List<TodoItemTask> findAll() {
        List<TodoItemTask> todoItemTasks = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                TodoItemTask todoItemTask = new TodoItemTask(
                        resultSet.getInt("id"),
                        null, // Implement logic to load TodoItem
                        null // Implement logic to load Person (assignee)
                );
                todoItemTask.setAssigned(resultSet.getBoolean("assigned"));
                todoItemTasks.add(todoItemTask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItemTasks;
    }

    @Override
    public void remove(TodoItemTask todoItemTask) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TASK)) {
            statement.setInt(1, todoItemTask.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TodoItemTask> findByAssignedStatus(boolean status) {
        List<TodoItemTask> todoItemTasks = new ArrayList<>();
        String query = "SELECT * FROM todo_item_task WHERE assigned = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, status);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TodoItemTask todoItemTask = new TodoItemTask(
                        resultSet.getInt("id"),
                        null, // Implement logic to load TodoItem
                        null // Implement logic to load Person (assignee)
                );
                todoItemTask.setAssigned(resultSet.getBoolean("assigned"));
                todoItemTasks.add(todoItemTask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItemTasks;
    }

    @Override
    public List<TodoItemTask> findByPersonId(int personId) {
        List<TodoItemTask> todoItemTasks = new ArrayList<>();
        String query = "SELECT * FROM todo_item_task WHERE assignee_id = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, personId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TodoItemTask todoItemTask = new TodoItemTask(
                        resultSet.getInt("id"),
                        null, // Implement logic to load TodoItem
                        null // Implement logic to load Person (assignee)
                );
                todoItemTask.setAssigned(resultSet.getBoolean("assigned"));
                todoItemTasks.add(todoItemTask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItemTasks;
    }
}
