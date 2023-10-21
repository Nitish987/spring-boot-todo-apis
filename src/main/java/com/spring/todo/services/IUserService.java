package com.spring.todo.services;

import com.spring.todo.models.User;

public interface IUserService {
    User createUser(String name, String email, String password);
    User readUser(int id);
    User readUserByEmail(String email);
    User updateUserName(int id, String name);
    User updateUserPassword(int id, String password);
    void deleteUser(int id);

}
