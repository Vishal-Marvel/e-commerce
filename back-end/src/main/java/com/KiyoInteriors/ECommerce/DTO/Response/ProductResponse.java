package com.KiyoInteriors.ECommerce.DTO.Response;

import com.KiyoInteriors.ECommerce.entity.Image;
import com.KiyoInteriors.ECommerce.entity.Product;
import com.KiyoInteriors.ECommerce.entity.ReviewRating;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private String id;
    private String productName;
    private String productDescription;
    private Double productPrize;
    private String category;
    private List<Image> productPics;
    private String model;
    private List<String> sizes;
    private List<String> colors;
    private List<ReviewRating> reviewRatingMap;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.productPrize = product.getProductPrize();
        this.category = product.getCategory().toString();
        this.productPics = product.getProductPics();
        this.model = product.getModel();
        this.colors = product.getColors();
        this.sizes = product.getSizes();
        this.reviewRatingMap = product.getReviewRating().values().stream().toList();
    }
}
