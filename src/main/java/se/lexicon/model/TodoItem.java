package se.lexicon.model;

import java.time.LocalDate;

public class TodoItem {
    private int id;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;
    private Person creator;


    public TodoItem(int id, String title, String description, LocalDate deadline, Person creator) {
        if (title == null || title.isEmpty() || deadline == null || creator == null) {
            throw new IllegalArgumentException("Title, deadline, and creator cannot be null or empty");
        }
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.creator = creator;
        this.done = false; // default value
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        if (deadline == null) {
            throw new IllegalArgumentException("Deadline cannot be null");
        }
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        if (creator == null) {
            throw new IllegalArgumentException("Creator cannot be null");
        }
        this.creator = creator;
    }

    public String getSummary() {
        return String.format("{id: %d, title: %s, description: %s, deadline: %s, done: %b, creator: %s}",
                id, title, description, deadline, done, creator.getSummary());
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(deadline);
    }

}
