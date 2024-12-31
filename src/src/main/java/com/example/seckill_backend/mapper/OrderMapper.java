package com.example.seckill_backend.mapper;

import com.example.seckill_backend.model.Order;
import com.example.seckill_backend.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    // 创建订单
    int insertOrder(Order order);

    // 插入订单商品项
    int insertOrderItem(OrderItem orderItem);

    // 更新订单状态为取消
    int updateOrderStatusToCancelled(@Param("orderId") Integer orderId);

    // 获取订单商品项
    List<OrderItem> getOrderItemsByOrderId(@Param("orderId") Integer orderId);

    // 删除订单商品项
    int deleteOrderItemsByOrderId(@Param("orderId") Integer orderId);

    // 删除订单
    int deleteOrderById(@Param("orderId") Integer orderId);

    // 恢复商品库存
    int updateProductStock(@Param("productId") Integer productId, @Param("quantity") Integer quantity);

    // 根据订单ID获取订单信息
    Order getOrderById(@Param("orderId") Integer orderId);
    // 获取用户订单列表

    List<Order> getOrdersByUserId(@Param("userId") Integer userId);

    // 获取订单详情
    Order getOrderDetails(@Param("orderId") Integer orderId);
}
