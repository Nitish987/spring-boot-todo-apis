package com.spring.todo.services;

import com.spring.todo.models.Todo;

import java.util.List;

public interface ITodoService {
    Todo createTodo(String title, String description, String email);
    List<Todo> readTodoList(String email);
    Todo updateTodo(int id, String title, String description, String email);
    Todo updateTodoCompletion(int id, boolean isCompleted, String email);
    void deleteTodo(int id, String email);
}
