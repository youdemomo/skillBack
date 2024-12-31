package com.example.seckill_backend.service;

import com.example.seckill_backend.mapper.UserMapper;
import com.example.seckill_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String register(User user) {
        // 检查用户名是否已存在
        if (userMapper.getUserByName(user.getName()) != null) {
            return "用户名已存在，请重新输入";
        }
        user.setStatus(1);  // 默认普通用户，status = 1
        userMapper.insertUser(user);  // 插入用户时数据库自动生成 id
        return "注册成功";
    }

    public String login(String name, String password) {
        // 用户名和密码登录
        User user = userMapper.login(name, password);
        if (user != null) {
            return (user.getStatus() == 0 ? "管理员" : "用户") + "," + user.getId();
        }
        return "用户名或密码错误";
    }

    public String retrievePassword(String name, String answer) {
        // 通过用户名和找回密码答案找回密码
        User user = userMapper.validatePasswordAnswer(name, answer);
        return user != null ? "您的密码是：" + user.getPassword() : "用户名或找回密码答案错误";
    }
}
