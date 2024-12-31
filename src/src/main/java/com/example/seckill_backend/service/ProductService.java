package com.example.seckill_backend.service;

import com.example.seckill_backend.mapper.ProductMapper;
import com.example.seckill_backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    // 获取所有商品的名称和图片URL
    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();
    }

    // 根据商品名称模糊查询
    public List<Product> searchProductsByName(String name) {
        return productMapper.searchProductsByName(name);
    }

    // 获取商品详情
    public Product getProductById(Integer id) {
        return productMapper.getProductById(id);
    }

    // 添加新商品
    public String addProduct(Product product) {
        int result = productMapper.insertProduct(product);
        return result > 0 ? "商品添加成功" : "商品添加失败";
    }
}
