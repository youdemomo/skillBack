package com.example.seckill_backend.mapper;

import com.example.seckill_backend.model.SeckillActivity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SeckillActivityMapper {
    // 插入新活动
    int insertSeckillActivity(SeckillActivity activity);

    // 获取所有秒杀活动
    List<SeckillActivity> getAllSeckillActivities();
}
