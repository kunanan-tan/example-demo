package com.example.demo.controller;

import com.example.demo.bean.UserBean;
import com.example.demo.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserBean request) {
        return ResponseEntity.ok(userServiceImpl.login(request));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserBean request){
        return ResponseEntity.ok(userServiceImpl.create(request));
    }
}
