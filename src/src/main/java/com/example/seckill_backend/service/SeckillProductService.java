package com.example.seckill_backend.service;

import com.example.seckill_backend.mapper.SeckillProductMapper;
import com.example.seckill_backend.model.SeckillProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.seckill_backend.mapper.ProductMapper;

import java.util.List;
import java.util.Map;

@Service
public class SeckillProductService {

    @Autowired
    private SeckillProductMapper seckillProductMapper;
    @Autowired
    private ProductMapper productMapper;
    /**
     * 添加秒杀商品
     * @param seckillProduct 秒杀商品对象
     * @return 添加结果的字符串
     */
    public String addSeckillProduct(SeckillProduct seckillProduct) {
        // 检查商品是否已经是秒杀商品
        if (seckillProductMapper.getSeckillProductByProductId(seckillProduct.getProductId()) != null) {
            return "该商品已经是秒杀商品，请重新选择";
        }

        // 2. 添加秒杀商品到秒杀商品表
        seckillProductMapper.addSeckillProduct(seckillProduct);

// 更新 product 表的 type 字段为 1
        productMapper.updateProductType(seckillProduct.getProductId(), 1);
        return "秒杀商品添加成功";
    }

    /**
     * 获取秒杀商品信息
     * @param productId 商品ID
     * @return 秒杀商品信息
     */
    public SeckillProduct getSeckillProductByProductId(int productId) {
        return seckillProductMapper.getSeckillProductByProductId(productId);
    }



//    /**
//     * 获取所有秒杀商品的名称和图片信息
//     * @return 秒杀商品的名称和图片信息列表
//     */
//    public List<Map<String, Object>> getAllSeckillProductNamesAndImages() {
//        return seckillProductMapper.getAllSeckillProductNamesAndImages();
//    }

    /**
     * 根据活动ID获取所有秒杀商品
     * @param activityId 活动ID
     * @return 秒杀商品列表
     */
    public List<SeckillProduct> getSeckillProductsByActivityId(int activityId) {
        return seckillProductMapper.getSeckillProductsByActivityId(activityId);
    }
    /**
     * 根据活动ID和商品ID获取单个秒杀商品的信息
     * @param activityId 活动ID
     * @param productId 商品ID
     * @return 秒杀商品信息
     */
    public SeckillProduct getSeckillProductByActivityAndProductId(int activityId, int productId) {
        return seckillProductMapper.getSeckillProductByActivityAndProductId(activityId, productId);
    }


    // 删除秒杀商品
    public String deleteSeckillProduct(int activityId, int productId) {
        try {
            // 删除秒杀商品
            int rowsAffected = seckillProductMapper.deleteSeckillProduct(activityId, productId);
            if (rowsAffected > 0) {
                // 删除成功后，更新 product 表中的 type 字段为 0
                productMapper.updateProductTypeToNormal(productId);
                return "秒杀商品删除成功";
            } else {
                return "秒杀商品不存在或删除失败";
            }
        } catch (Exception e) {
            return "删除秒杀商品失败: " + e.getMessage();
        }
    }
}
