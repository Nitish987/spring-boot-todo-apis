package com.spring.todo.services;

import com.spring.todo.models.Todo;
import com.spring.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService implements ITodoService {
    @Autowired
    private TodoRepository repository;
    @Autowired
    private UserService service;

    @Override
    public Todo createTodo(String title, String description, String email) {
        Todo todo = new Todo();
        todo.setCurrentCreatedOn();
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setUser(service.readUserByEmail(email));
        return repository.save(todo);
    }

    @Override
    public List<Todo> readTodoList(String email) {
        return repository.getAllTodoByUser(service.readUserByEmail(email));
    }

    @Override
    public Todo updateTodo(int id, String title, String description, String email) {
        Todo todo = repository.getTodoByUser(id, service.readUserByEmail(email));
        todo.setTitle(title);
        todo.setDescription(description);
        return repository.save(todo);
    }

    @Override
    public Todo updateTodoCompletion(int id, boolean isCompleted, String email) {
        Todo todo = repository.getTodoByUser(id, service.readUserByEmail(email));
        todo.setCompleted(isCompleted);
        return repository.save(todo);
    }

    @Override
    public void deleteTodo(int id, String email) {
        repository.deleteTodoByUser(id, service.readUserByEmail(email));
    }
}
