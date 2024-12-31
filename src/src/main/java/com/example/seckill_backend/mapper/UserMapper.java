package com.example.seckill_backend.mapper;

import com.example.seckill_backend.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    // 根据用户名查询用户
    User getUserByName(@Param("name") String name);

    // 插入用户
    int insertUser(User user);

    // 登录验证
    User login(@Param("name") String name, @Param("password") String password);

    // 找回密码验证
    User validatePasswordAnswer(@Param("name") String name, @Param("findPasswordAnswer") String findPasswordAnswer);
}
