package se.lexicon.model;

import java.util.Objects;

public class ToDoItemTask {
    private int id;
    private boolean assigned;
    private ToDoItem toDoItem;
    private Person assignee;


    public ToDoItemTask(int id, ToDoItem toDoItem, Person assignee) {
        if (toDoItem == null) {
            throw new IllegalArgumentException("TodoItem cannot be null");
        }
        this.id = id;
        this.toDoItem = toDoItem;
        this.assignee = assignee;
        this.assigned = (assignee != null);
    }


    public int getId() {
        return id;
    }
    public boolean isAssigned() {
        return assigned;
    }
    public ToDoItem getToDoItem() {
        return toDoItem;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Person getAssignee() {
        return assignee;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    //Setters
    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }


        public void setToDoItem(ToDoItem toDoItem) {
            if (toDoItem == null) {
                throw new IllegalArgumentException("TodoItem cannot be null");
            }
            this.toDoItem = toDoItem;
        }
        public void setAssignee(Person assignee) {
            this.assignee = assignee;
            this.assigned = (assignee != null);
        }
        //Summary
        public String toString() {
            return "TodoItemTask{" +
                    "todoItem=" + toDoItem +
                    ", assigned=" + assigned +
                    ", id=" + id +
                    '}';
        }

    }
