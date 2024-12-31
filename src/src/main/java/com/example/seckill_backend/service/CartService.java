package com.example.seckill_backend.service;

import com.example.seckill_backend.mapper.CartMapper;
import com.example.seckill_backend.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;

    // 添加商品到购物车
    public String addToCart(Integer userId, Integer productId, Integer quantity) {
        int result = cartMapper.addToCart(userId, productId, quantity);
        if (result > 0) {
            return "商品已成功添加到购物车！";
        }
        return "添加商品到购物车失败，请重试。";
    }

    // 获取用户购物车中的商品
    public List<Cart> getCartByUserId(Integer userId) {
        return cartMapper.getCartByUserId(userId);
    }

    // 从购物车删除商品
    public String deleteFromCart(Integer userId, Integer productId) {
        int result = cartMapper.deleteFromCart(userId, productId);
        if (result > 0) {
            return "商品已从购物车中删除。";
        }
        return "删除商品失败，请重试。";
    }
}
