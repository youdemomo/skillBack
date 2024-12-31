package com.example.seckill_backend.controller;

import com.example.seckill_backend.model.SeckillProduct;
import com.example.seckill_backend.service.SeckillProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/seckill")
public class SeckillProductController {

    private final SeckillProductService seckillProductService;

    public SeckillProductController(SeckillProductService seckillProductService) {
        this.seckillProductService = seckillProductService;
    }

    @PostMapping("/add")
    public String addSeckillProduct(@RequestBody SeckillProduct seckillProduct) {
        try {
            // 调用服务层方法并获取返回结果
            String result = seckillProductService.addSeckillProduct(seckillProduct);
            return result; // 返回服务层结果
        } catch (Exception e) {
            return "秒杀商品添加失败: " + e.getMessage();
        }
    }

//    /**
//     * 获取所有秒杀商品的名称和图片信息
//     * @return 秒杀商品的名称和图片信息列表
//     */
//    @GetMapping("/products")
//    public List<Map<String, Object>> getAllSeckillProductNamesAndImages() {
//        return seckillProductService.getAllSeckillProductNamesAndImages();
//    }

    /**
     * 根据活动ID获取所有秒杀商品
     * @param activityId 活动ID
     * @return 秒杀商品列表
     */
    @GetMapping("/products/{activityId}")
    public List<SeckillProduct> getSeckillProductsByActivityId(@PathVariable int activityId) {
        return seckillProductService.getSeckillProductsByActivityId(activityId);
    }

    // 根据活动ID和商品ID获取单个秒杀商品信息
    @GetMapping("/products/{activityId}/{productId}")
    public SeckillProduct getSeckillProduct(@PathVariable int activityId, @PathVariable int productId) {
        return seckillProductService.getSeckillProductByActivityAndProductId(activityId, productId);
    }
    // 删除秒杀商品
    @DeleteMapping("/delete/{activityId}/{productId}")
    public String deleteSeckillProduct(@PathVariable int activityId, @PathVariable int productId) {
        try {
            // 调用服务层方法删除秒杀商品
            String result = seckillProductService.deleteSeckillProduct(activityId, productId);
            return result; // 返回服务层的结果
        } catch (Exception e) {
            return "删除秒杀商品失败: " + e.getMessage();
        }
    }
}
