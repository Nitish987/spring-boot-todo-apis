package com.spring.todo.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity(name = "todo_task")
public class Todo {
    @Id
    @GeneratedValue()
    private int id;
    private String title;
    private String description;
    private Date createdOn;
    private boolean isCompleted;
    @ManyToOne
    private User user;

    public Todo() {
    }

    public Todo(int id, String title, String description, Date createdOn, boolean isCompleted, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdOn = createdOn;
        this.isCompleted = isCompleted;
        this.user = user;
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
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setCurrentCreatedOn() {
        this.createdOn = new Date();
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("description", description);
        map.put("isCompleted", isCompleted);
        map.put("createdOn", createdOn);
        return map;
    }

    public static List<Map<String, Object>> toList(List<Todo> todos) {
        return todos.stream().map(Todo::toMap).toList();
    }

    @Override
    public String toString() {
        return "Todo{" + "id=" + id + ", title='" + title + '\'' + ", description='" + description + '\'' + ", createdOn=" + createdOn + ", isCompleted=" + isCompleted + '}';
    }
}
