package com.KiyoInteriors.ECommerce.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Document
public class Product {
    @Id
    private String id = UUID.randomUUID().toString();
    private String productName;
    private String productDescription;
    private Double productPrize;
    private List<String> sizes;
    private List<String> colors;
    private String model;
    private Category category;
    private List<Image> productPics;
    private Map<String,ReviewRating> reviewRating = new HashMap<>();
    private Double rating=0.0;
}
