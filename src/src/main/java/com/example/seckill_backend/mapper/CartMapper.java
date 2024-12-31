package com.example.seckill_backend.mapper;

import com.example.seckill_backend.model.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {


    // 添加商品到购物车
    int addToCart(@Param("userId") Integer userId, @Param("productId") Integer productId, @Param("quantity") Integer quantity);

    // 获取用户购物车中的商品
    List<Cart> getCartByUserId(@Param("userId") Integer userId);

    // 删除购物车中的商品
    int deleteFromCart(@Param("userId") Integer userId, @Param("productId") Integer productId);
}
