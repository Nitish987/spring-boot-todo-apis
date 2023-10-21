package com.spring.todo.controllers;

import com.spring.todo.models.User;
import com.spring.todo.models.UserInfo;
import com.spring.todo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody UserInfo userInfo) {
        try {
            User user = service.createUser(userInfo.getName(), userInfo.getEmail(), userInfo.getPassword());
            return new ResponseEntity<>(getResponse(true, user.toMap(), null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(getResponse(false, null, e.getMessage()), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserInfo userInfo) {
        try {
            try {
                manager.authenticate(new UsernamePasswordAuthenticationToken(userInfo.getEmail(), userInfo.getPassword()));
            } catch (Exception e) {
                return new ResponseEntity<>(getResponse(false, null, e.getMessage()), HttpStatus.OK);
            }
            String token = service.generateToken(userInfo.getEmail());
            return new ResponseEntity<>(getResponse(true, token, null), HttpStatus.OK);
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
