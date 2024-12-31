package com.example.seckill_backend.model;

import java.math.BigDecimal;

public class SeckillProduct {
    private Integer id;              // 秒杀商品ID
    private Integer activityId;      // 关联活动ID
    private Integer productId;       // 商品ID
    private BigDecimal price;        // 秒杀价格
  //  private Integer stock;           // 秒杀库存
    private SeckillActivity activity; // 关联的秒杀活动
    private String productName;    // 商品名称 (从 product 表获取)
    private String productImageUrl;// 商品图片URL (从 product 表获取)
    private Product product;  // 如果需要完整的商品信息
    // Getter 和 Setter 方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

//    public Integer getStock() {
//        return stock;
//    }
//
//    public void setStock(Integer stock) {
//        this.stock = stock;
//    }

    public SeckillActivity getActivity() {
        return activity;
    }

    public void setActivity(SeckillActivity activity) {
        this.activity = activity;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    // Getter 和 Setter 方法
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
