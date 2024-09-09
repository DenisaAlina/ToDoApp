package se.lexicon.dao;

import se.lexicon.model.TodoItem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoItemDAOJdbcImpl implements TodoItemDAO{
    private static final String INSERT_TODO = "INSERT INTO todo_item (title, description, deadline, done, creator_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM todo_item WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM todo_item";
    private static final String DELETE_TODO = "DELETE FROM todo_item WHERE id = ?";

    @Override
    public TodoItem persist(TodoItem todoItem) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_TODO, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, todoItem.getTitle());
            statement.setString(2, todoItem.getDescription());
            statement.setDate(3, Date.valueOf(todoItem.getDeadline()));
            statement.setBoolean(4, todoItem.isDone());
            statement.setInt(5, todoItem.getCreator().getId());

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    todoItem.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItem;
    }

    @Override
    public TodoItem findById(int id) {
        TodoItem todoItem = null;
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                todoItem = new TodoItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        null
                );
                todoItem.setDone(resultSet.getBoolean("done"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItem;
    }

    @Override
    public List<TodoItem> findAll() {
        List<TodoItem> todoItems = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                TodoItem todoItem = new TodoItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        null
                );
                todoItem.setDone(resultSet.getBoolean("done"));
                todoItems.add(todoItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItems;
    }

    @Override
    public void remove(TodoItem todoItem) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TODO)) {
            statement.setInt(1, todoItem.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TodoItem> findAllByDoneStatus(boolean doneStatus) {
        List<TodoItem> todoItems = new ArrayList<>();
        String query = "SELECT * FROM todo_item WHERE done = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, doneStatus);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TodoItem todoItem = new TodoItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        null
                );
                todoItem.setDone(resultSet.getBoolean("done"));
                todoItems.add(todoItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItems;
    }

    @Override
    public List<TodoItem> findByTitleContains(String title) {
        List<TodoItem> todoItems = new ArrayList<>();
        String query = "SELECT * FROM todo_item WHERE title LIKE ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + title + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TodoItem todoItem = new TodoItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        null
                );
                todoItem.setDone(resultSet.getBoolean("done"));
                todoItems.add(todoItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItems;
    }

    @Override
    public List<TodoItem> findByPersonId(int personId) {
        List<TodoItem> todoItems = new ArrayList<>();
        String query = "SELECT * FROM todo_item WHERE creator_id = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, personId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TodoItem todoItem = new TodoItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        null
                );
                todoItem.setDone(resultSet.getBoolean("done"));
                todoItems.add(todoItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItems;
    }

    @Override
    public List<TodoItem> findByDeadlineBefore(LocalDate date) {
        List<TodoItem> todoItems = new ArrayList<>();
        String query = "SELECT * FROM todo_item WHERE deadline < ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TodoItem todoItem = new TodoItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        null
                );
                todoItem.setDone(resultSet.getBoolean("done"));
                todoItems.add(todoItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItems;
    }

    @Override
    public List<TodoItem> findByDeadlineAfter(LocalDate date) {
        List<TodoItem> todoItems = new ArrayList<>();
        String query = "SELECT * FROM todo_item WHERE deadline > ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TodoItem todoItem = new TodoItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        null
                );
                todoItem.setDone(resultSet.getBoolean("done"));
                todoItems.add(todoItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItems;
    }


}



