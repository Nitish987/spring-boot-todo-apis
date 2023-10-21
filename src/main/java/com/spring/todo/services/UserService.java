package com.spring.todo.services;

import com.spring.todo.models.User;
import com.spring.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService, IAuthService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private JwtService jwtService;

    @Override
    public User createUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return repository.save(user);
    }

    @Override
    public User readUser(int id) {
        return repository.getReferenceById(id);
    }

    @Override
    public User readUserByEmail(String email) {
        return repository.getUserByEmail(email);
    }

    @Override
    public User updateUserName(int id, String name) {
        User user = readUser(id);
        user.setName(name);
        return repository.save(user);
    }

    @Override
    public User updateUserPassword(int id, String password) {
        User user = readUser(id);
        user.setPassword(password);
        return repository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        repository.deleteById(id);
    }

    @Override
    public String generateToken(String email) {
        User user = repository.getUserByEmail(email);
        return jwtService.generateToken(user.getId(), user.getEmail());
    }
}
