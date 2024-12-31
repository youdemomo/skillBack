package com.example.seckill_backend.controller;

import com.example.seckill_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.seckill_backend.model.Order;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 直接购买商品并创建订单
    @PostMapping("/buy")
    public String buyProduct(@RequestParam Integer userId, @RequestParam Integer productId, @RequestParam Integer quantity) {
        return orderService.buyProduct(userId, productId, quantity);
    }
    // 取消订单
    @PutMapping("/cancel")
    public String cancelOrder(@RequestParam Integer orderId) {
        return orderService.cancelOrder(orderId);
    }

    // 直接购买购物车商品并创建订单
    @PostMapping("/buyFromCart")
    public String buyFromCart(@RequestParam Integer userId) {
        return orderService.buyFromCart(userId);
    }
    // 查看用户订单列表
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Integer userId) {
        return orderService.getOrdersByUserId(userId);
    }

    // 查看订单详情
    @GetMapping("orderid/{orderId}")
    public Order getOrderDetails(@PathVariable Integer orderId) {
        return orderService.getOrderDetails(orderId);
    }
}
