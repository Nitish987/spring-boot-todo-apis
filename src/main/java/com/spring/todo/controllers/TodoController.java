package com.spring.todo.controllers;

import com.spring.todo.models.Todo;
import com.spring.todo.models.TodoInfo;
import com.spring.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService service;

    @PostMapping("/add")
    public ResponseEntity<Object> addTodo(Authentication authentication, @RequestBody TodoInfo todoInfo) {
        try {
            Todo data = service.createTodo(todoInfo.getTitle(), todoInfo.getDescription(), authentication.getName());
            return new ResponseEntity<>(getResponse(true, data.toMap(), null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(getResponse(false, null, e.getMessage()), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getTodoList(Authentication authentication) {
        try {
            List<Todo> data = service.readTodoList(authentication.getName());
            return new ResponseEntity<>(getResponse(true, Todo.toList(data), null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(getResponse(false, null, e.getMessage()), HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateTodo(Authentication authentication, @PathVariable int id, @RequestBody TodoInfo todoInfo) {
        try {
            Todo data = service.updateTodo(id, todoInfo.getTitle(), todoInfo.getDescription(), authentication.getName());
            return new ResponseEntity<>(getResponse(true, data.toMap(), null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(getResponse(false, null, e.getMessage()), HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/completed/{id}")
    public ResponseEntity<Object> setTodoCompleted(Authentication authentication, @PathVariable int id, @RequestBody Map<String, Boolean> complete) {
        try {
            Todo data = service.updateTodoCompletion(id, complete.get("isCompleted"), authentication.getName());
            return new ResponseEntity<>(getResponse(true, data.toMap(), null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(getResponse(false, null, e.getMessage()), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTodo(Authentication authentication, @PathVariable int id) {
        try {
            service.deleteTodo(id, authentication.getName());
            return new ResponseEntity<>(getResponse(true, null, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(getResponse(false, null, e.getMessage()), HttpStatus.FORBIDDEN);
        }
    }

    public Map<String, Object> getResponse(boolean success, Object data, String error) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", success);
        map.put("data", data);
        map.put("error", error);
        return map;
    }
}
