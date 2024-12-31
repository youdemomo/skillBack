package com.example.seckill_backend.mapper;

import com.example.seckill_backend.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    // 获取所有商品，只查询名称和图片URL
    List<Product> getAllProducts();

    // 根据商品名称模糊查询
    List<Product> searchProductsByName(@Param("name") String name);

    // 获取商品详情
    Product getProductById(@Param("id") Integer id);

    // 更新商品库存
    int updateProductStock(Product product);
    // 插入新商品
    int insertProduct(Product product);
    //改变类型
    void updateProductType(@Param("productId") int productId, @Param("type") int type);
    void updateProductTypeToNormal(@Param("productId") int productId);
}
