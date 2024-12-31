package com.example.seckill_backend.controller;

import com.example.seckill_backend.model.Cart;
import com.example.seckill_backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // 添加商品到购物车
    @PostMapping("/add")
    public String addToCart(@RequestParam Integer userId, @RequestParam Integer productId, @RequestParam Integer quantity) {
        return cartService.addToCart(userId, productId, quantity);
    }

    // 获取用户购物车中的商品
    @GetMapping("/get")
    public List<Cart> getCartByUserId(@RequestParam Integer userId) {
        return cartService.getCartByUserId(userId);
    }

    // 从购物车删除商品
    @DeleteMapping("/delete")
    public String deleteFromCart(@RequestParam Integer userId, @RequestParam Integer productId) {
        return cartService.deleteFromCart(userId, productId);
    }
}
