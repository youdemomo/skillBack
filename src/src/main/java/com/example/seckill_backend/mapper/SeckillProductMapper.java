package com.example.seckill_backend.mapper;

import com.example.seckill_backend.model.SeckillProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SeckillProductMapper {

    // 添加秒杀商品
    void addSeckillProduct(SeckillProduct seckillProduct);

//    // 更新商品库存（普通商品库存）
//    void updateProductStock(@Param("productId") int productId, @Param("stock") int stock);

    // 通过商品ID获取秒杀商品
    SeckillProduct getSeckillProductByProductId(int productId);

//    // 更新秒杀商品库存
//    int updateSeckillProductStock(@Param("productId") int productId, @Param("stock") int stock);
//
    // 获取商品的当前库存
//    int getProductStock(@Param("productId") Integer productId);
    // 查询所有秒杀商品的名称和图片信息

    //List<Map<String, Object>> getAllSeckillProductNamesAndImages();
    // 根据活动ID获取所有秒杀商品
    List<SeckillProduct> getSeckillProductsByActivityId(@Param("activityId") int activityId);
    // 根据活动ID和商品ID获取秒杀商品
    SeckillProduct getSeckillProductByActivityAndProductId(@Param("activityId") int activityId, @Param("productId") int productId);

    // 删除秒杀商品

    int deleteSeckillProduct(@Param("activityId") int activityId, @Param("productId") int productId);
}
