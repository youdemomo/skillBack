package com.example.seckill_backend.controller;

import com.example.seckill_backend.model.Product;
import com.example.seckill_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 获取所有商品
    @GetMapping("/list")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();  // 仅返回商品的名称和图片URL
    }

    // 根据商品名称模糊查询
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        return productService.searchProductsByName(name);  // 返回搜索结果（名称和图片）
    }

    // 获取商品详情
    @GetMapping("/details/{id}")
    public Product getProductDetails(@PathVariable Integer id) {
        return productService.getProductById(id);  // 返回完整的商品详情
    }

    // 添加新商品
    @PostMapping("/add")
    public String addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
