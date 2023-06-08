package com.KiyoInteriors.ECommerce.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/**
 * This class represents a product entity that can be stored in a database.
 * It has various attributes that describe the product, such as name, description, prize, sizes, colors, model, category and pictures.
 * It also has an id field that is automatically generated using UUID.
 */
@Data
@Document
public class Product {
    @Id
    private String id = UUID.randomUUID().toString();
    private String productName;
    private String productDescription;
    private Double costPrice;
    private Double profitPercentage;
    private Double MRP;
    private List<String> coupons = new ArrayList<>();
    private Double price;
    private List<String> sizes;
    private List<String> colors;
    private String model;
    private Category category;
    private List<Image> productPics;
    private Map<String, ReviewRating> reviewRating = new HashMap<>();
    private Double rating = 0.0;
    private Integer quantity;
}
