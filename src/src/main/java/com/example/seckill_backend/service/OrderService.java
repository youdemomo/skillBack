package com.example.seckill_backend.service;

import com.example.seckill_backend.mapper.CartMapper;
import com.example.seckill_backend.mapper.OrderMapper;
import com.example.seckill_backend.mapper.ProductMapper;
import com.example.seckill_backend.model.Cart;
import com.example.seckill_backend.model.Order;
import com.example.seckill_backend.model.OrderItem;
import com.example.seckill_backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    // 直接购买商品并创建订单
    public String buyProduct(Integer userId, Integer productId, Integer quantity) {
        // 获取商品信息
        Product product = productMapper.getProductById(productId);
        if (product == null) {
            return "商品不存在";
        }

        // 检查库存
        if (product.getStock() < quantity) {
            return "库存不足，无法购买";
        }

        // 扣减库存
        product.setStock(product.getStock() - quantity);
        int updateResult = productMapper.updateProductStock(product);
        if (updateResult <= 0) {
            return "更新库存失败";
        }

        // 创建订单
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus("PENDING");
        order.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());

        // 插入订单
        int orderResult = orderMapper.insertOrder(order);
        if (orderResult > 0) {
            // 创建订单商品项
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setProductId(productId);
            orderItem.setQuantity(quantity);
            orderItem.setPrice(product.getPrice());

            // 插入订单项
            orderMapper.insertOrderItem(orderItem);
            return "订单创建成功";
        }

        return "订单创建失败";
    }


    // 取消订单（将订单状态更新为取消，删除商品项，恢复商品库存）
    @Transactional
    public String cancelOrder(Integer orderId) {
        // 查询订单
        Order order = orderMapper.getOrderById(orderId);
        if (order == null) {
            return "订单不存在";
        }

        // 如果订单状态是 "待付款" 或 "已付款" 可以取消
        if (order.getStatus().equals("PENDING") || order.getStatus().equals("PAID")) {
            // 更新订单状态为取消
            orderMapper.updateOrderStatusToCancelled(orderId);

            // 获取订单商品项并恢复商品库存
            List<OrderItem> orderItems = orderMapper.getOrderItemsByOrderId(orderId);
            for (OrderItem item : orderItems) {
                // 恢复商品库存
                orderMapper.updateProductStock(item.getProductId(), item.getQuantity());
            }

            // 删除订单商品项
            orderMapper.deleteOrderItemsByOrderId(orderId);

            // 删除订单
            orderMapper.deleteOrderById(orderId);

            return "订单已取消，库存已恢复";
        } else {
            return "订单状态不可取消";
        }
    }

    // 直接购买商品
    public String buyFromCart(Integer userId) {
        // 1. 获取用户购物车中的所有商品
        List<Cart> cartItems = cartMapper.getCartByUserId(userId);

        if (cartItems.isEmpty()) {
            return "购物车为空！";
        }

        // 2. 创建订单
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus("PENDING");
        order.setCreatedAt(new java.util.Date());
        order.setUpdatedAt(new java.util.Date());

        // 计算总价
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Cart cartItem : cartItems) {
            Product product = cartItem.getProduct(); // 获取商品信息
            // 将数量转换为 BigDecimal，然后与商品价格相乘
            BigDecimal quantity = new BigDecimal(cartItem.getQuantity());
            totalPrice = totalPrice.add(product.getPrice().multiply(quantity));
        }
        order.setTotalPrice(totalPrice);

        // 3. 插入订单
        orderMapper.insertOrder(order);

        // 4. 插入订单商品项
        for (Cart cartItem : cartItems) {
            Product product = cartItem.getProduct(); // 获取商品信息
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(product.getPrice());

            orderMapper.insertOrderItem(orderItem);

            // 5. 更新商品库存
            orderMapper.updateProductStock(cartItem.getProductId(), cartItem.getQuantity());
        }

        // 6. 删除购物车中的商品
        for (Cart cartItem : cartItems) {
            cartMapper.deleteFromCart(userId, cartItem.getProductId());
        }

        return "订单已创建，商品已从购物车中移除！";
    }

    /**
     * 查看订单基本信息和状态
     */
    public Order getOrderDetails(Integer orderId) {
        return orderMapper.getOrderDetails(orderId);
    }

    /**
     * 查看用户所有订单
     */
    public List<Order> getOrdersByUserId(Integer userId) {
        return orderMapper.getOrdersByUserId(userId);
    }
}
