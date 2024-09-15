package se.lexicon.model;

import java.time.LocalDate;

public class ToDoItem {
    private int id;
    private String title;
    private String taskDescription;
    private LocalDate deadLine;
    private boolean done;
    private int creator;
    public ToDoItem(int id, String title, String taskDescription, LocalDate deadLine, boolean done, int creator) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (deadLine == null) {
            throw new IllegalArgumentException("Deadline cannot be null.");
        }
        this.id = id;
        this.title = title;
        this.taskDescription = taskDescription;
        this.deadLine = deadLine;
        this.done = done;
        this.creator = creator;
    }
    //Getters
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getTaskDescription() {
        return taskDescription;
    }
    public LocalDate getDeadLine() {
        return deadLine;
    }
    public boolean isDone() {
        return done;
    }

    public int getCreator() {
        return creator;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public void setDeadLine(LocalDate deadLine) {
        if (deadLine == null) {
            throw new IllegalArgumentException("Deadline cannot be null.");
        }
        this.deadLine = deadLine;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void setCreator(Person creator) {

        this.creator = creator.getId();
    }
    //isOverdue
    public boolean isOverdue() {
        return LocalDate.now().isAfter(deadLine);
    }
    //Summary
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                '}';
    }
    public int getCreatorId() {
        return 0;
    }

}
