package se.lexicon.dao;

import se.lexicon.model.Person;
import se.lexicon.model.ToDoItem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoItemDAOJdbcImpl implements TodoItemDAO{
    Connection connection = se.lexicon.dao.MySQLConnection.getConnection();

    @Override
    public ToDoItem create(ToDoItem todoItem) {
        String CREATE_TODO_ITEM = "INSERT INTO todo_item (title, description, deadline, done, assignee_id) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TODO_ITEM, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, todoItem.getTitle());
            preparedStatement.setString(2, todoItem.getTaskDescription());
            preparedStatement.setDate(3, Date.valueOf(todoItem.getDeadLine()));
            preparedStatement.setBoolean(4, todoItem.isDone());
            preparedStatement.setInt(5, todoItem.getCreatorId());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                todoItem.setId(resultSet.getInt(1));
            }

            return todoItem;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<ToDoItem> findAll() {
        List<ToDoItem> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM todo_item");

            while (resultSet.next()) {
                ToDoItem todoItem = new ToDoItem(
                        resultSet.getInt("todo_item_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")
                );
                todoItem.setId(resultSet.getInt("todo_item_id"));
                result.add(todoItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return List.of();
    }

    @Override
    public ToDoItem findById(int todoItemId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE todo_item_id = ?");
            preparedStatement.setInt(1, todoItemId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ToDoItem todoItem = new ToDoItem(
                        resultSet.getInt("todo_item_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")
                );
                todoItem.setId(resultSet.getInt("todo_item_id"));
                return todoItem;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Collection<ToDoItem> findByDoneStatus(boolean done) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE done = ?");
            preparedStatement.setBoolean(1, done);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<ToDoItem> result = new ArrayList<>();
            while (resultSet.next()) {
                ToDoItem todoItem = new ToDoItem(
                        resultSet.getInt("todo_item_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")
                );
                todoItem.setId(resultSet.getInt("todo_item_id"));
                result.add(todoItem);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<ToDoItem> findByAssignee(int assigneeId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE assignee_id = ?");
            preparedStatement.setInt(1, assigneeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<ToDoItem> result = new ArrayList<>();
            while (resultSet.next()) {
                ToDoItem todoItem = new ToDoItem(
                        resultSet.getInt("todo_item_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")
                );
                todoItem.setId(resultSet.getInt("todo_item_id"));
                result.add(todoItem);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<ToDoItem> findByAssignee(Person person) {
        return findByAssignee(person.getId());
    }

    @Override
    public Collection<ToDoItem> findByUnassignedTodoItems() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE assignee_id IS NULL");
            ResultSet resultSet = preparedStatement.executeQuery();

            List<ToDoItem> result = new ArrayList<>();
            while (resultSet.next()) {
                ToDoItem todoItem = new ToDoItem(
                        resultSet.getInt("todo_item_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")
                );
                todoItem.setId(resultSet.getInt("todo_item_id"));
                result.add(todoItem);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ToDoItem update(ToDoItem todoItem) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE todo_item SET title = ?, description = ?, deadline = ?, done = ?, assignee_id = ? WHERE todo_item_id = ?");
            preparedStatement.setString(1, todoItem.getTitle());
            preparedStatement.setString(2, todoItem.getTaskDescription());
            preparedStatement.setDate(3, Date.valueOf(todoItem.getDeadLine()));
            preparedStatement.setBoolean(4, todoItem.isDone());
            preparedStatement.setInt(5, todoItem.getCreatorId());
            preparedStatement.setInt(6, todoItem.getId());

            preparedStatement.executeUpdate();

            return todoItem;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean deleteById(int todoId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM todo_item WHERE todo_item_id = ?");
            preparedStatement.setInt(1, todoId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



