package com.spring.todo.services;

public interface IAuthService {
    String generateToken(String email);
}
