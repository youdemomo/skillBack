package com.example.seckill_backend.model;

public class User {

    private Long id;
    private String name;
    private String password;
    private Integer status = 1;  // 默认普通用户
    private String findPasswordAnswer;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFindPasswordAnswer() {
        return findPasswordAnswer;
    }

    public void setFindPasswordAnswer(String findPasswordAnswer) {
        this.findPasswordAnswer = findPasswordAnswer;
    }
}
