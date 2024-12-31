package com.example.seckill_backend.model;

import java.util.Date;

public class SeckillActivity {
    private Integer id;           // 活动ID
    private String title;         // 活动标题
    private Date startTime;       // 活动开始时间
    private Date endTime;         // 活动结束时间
    private String status;        // 活动状态：PENDING、ACTIVE、ENDED
    private Date createdAt;       // 创建时间
    private Date updatedAt;       // 更新时间

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
