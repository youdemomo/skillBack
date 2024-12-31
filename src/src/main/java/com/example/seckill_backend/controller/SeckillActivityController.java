package com.example.seckill_backend.controller;

import com.example.seckill_backend.model.SeckillActivity;
import com.example.seckill_backend.service.SeckillActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seckill-activity")
public class SeckillActivityController {

    private final SeckillActivityService seckillActivityService;

    @Autowired
    public SeckillActivityController(SeckillActivityService seckillActivityService) {
        this.seckillActivityService = seckillActivityService;
    }

    @PostMapping("/create")
    public String createSeckillActivity(@RequestBody SeckillActivity activity) {
        seckillActivityService.createSeckillActivity(activity);
        return "秒杀活动创建成功！";
    }

    /**
     * 获取所有秒杀活动
     * @return 秒杀活动列表
     */
    @GetMapping("/seckill/activities")
    public List<SeckillActivity> getAllSeckillActivities() {
        return seckillActivityService.getAllSeckillActivities();
    }
}
