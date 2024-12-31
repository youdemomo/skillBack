package com.example.seckill_backend.service;

import com.example.seckill_backend.model.SeckillActivity;
import com.example.seckill_backend.mapper.SeckillActivityMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeckillActivityService {

    private final SeckillActivityMapper seckillActivityMapper;

    public SeckillActivityService(SeckillActivityMapper seckillActivityMapper) {
        this.seckillActivityMapper = seckillActivityMapper;
    }

    public void createSeckillActivity(SeckillActivity activity) {
        // 设置初始状态为 PENDING
        activity.setStatus("PENDING");
        seckillActivityMapper.insertSeckillActivity(activity);
    }
    /**
     * 获取所有秒杀活动
     * @return 秒杀活动列表
     */
    public List<SeckillActivity> getAllSeckillActivities() {
        // 获取所有秒杀活动
        List<SeckillActivity> activities = seckillActivityMapper.getAllSeckillActivities();

        // 获取当前时间
        Date currentTime = new Date();

        // 更新活动状态为 "ENDED" 如果当前时间超过了活动的结束时间
        activities.forEach(activity -> {
            if (activity.getEndTime() != null && currentTime.after(activity.getEndTime())) {
                activity.setStatus("ENDED");
            }
        });

        // 过滤掉status不为PENDING的活动，只返回PENDING状态的活动
        return activities.stream()
                .filter(activity -> "PENDING".equals(activity.getStatus()) )
                .collect(Collectors.toList()); // 收集过滤后的活动列表
    }
}
