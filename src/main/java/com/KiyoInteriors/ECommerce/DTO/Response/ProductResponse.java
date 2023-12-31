package com.KiyoInteriors.ECommerce.DTO.Response;

import com.KiyoInteriors.ECommerce.entity.Product;
import com.KiyoInteriors.ECommerce.entity.ReviewRating;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that represents a product response object.
 * It contains the fields and constructor of a product entity.
 */
@Data
public class ProductResponse {
    private String id;
    private String productName;
    private String productDescription;
    private Integer productPrice;
    private Double MRP;
    private Integer discountPercentage;
    private List<String> productPics;
    private List<String> categories;
    private String model;
    private List<String> sizes;
    private List<String> colors;
    private List<ReviewRating> reviewRating;
    private Map<Integer, Integer> ratings;
    private Double rating;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.productPrice = product.getPrice();
        this.discountPercentage = (int)Math.round((product.getMRP()-product.getPrice())/product.getMRP()*100);
        this.MRP = product.getMRP();
        this.productPics = product.getProductPics();
        this.model = product.getModel();
        this.colors = product.getColors();
        this.categories = product.getCategories();
        this.sizes = product.getSizes();
        this.reviewRating = product.getReviewRating().values().stream().toList();
        this.ratings = getRatings(product.getReviewRating().values().stream().toList());
        this.rating = product.getRating();
    }
    public Map<Integer, Integer> getRatings(List<ReviewRating> reviewRatings){
        Map<Integer, Integer> tempRatings = new HashMap<>();
        for (ReviewRating tempReviewRating : reviewRatings){
            tempRatings.put(tempReviewRating.getRating(),
                    tempRatings.getOrDefault(tempReviewRating.getRating(), 0)+1);
        }
        return tempRatings;
    }
}
