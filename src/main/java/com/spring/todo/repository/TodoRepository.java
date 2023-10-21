package com.spring.todo.repository;

import com.spring.todo.models.Todo;
import com.spring.todo.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    @Query("select t from todo_task t where t.user = :u")
    List<Todo> getAllTodoByUser(@Param("u") User user);

    @Query("select t from todo_task t where t.id = :id and t.user = :u")
    Todo getTodoByUser(@Param("id") int todoId, @Param("u") User user);

    @Transactional
    @Modifying
    @Query("delete from todo_task t where t.id = :id and t.user = :u")
    void deleteTodoByUser(@Param("id") int todoId, @Param("u") User user);
}
