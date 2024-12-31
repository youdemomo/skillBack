package com.example.seckill_backend.controller;

import com.example.seckill_backend.service.UserService;
import com.example.seckill_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String result = userService.register(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String name, @RequestParam String password) {
        String result = userService.login(name, password);
        if ("用户名或密码错误".equals(result)) {
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);  // 设置401 Unauthorized
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/retrievePassword")
    public ResponseEntity<String> retrievePassword(@RequestParam String name, @RequestParam String answer) {
        String result = userService.retrievePassword(name, answer);
        if ("用户名或找回密码答案错误".equals(result)) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);  // 设置400 Bad Request
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
