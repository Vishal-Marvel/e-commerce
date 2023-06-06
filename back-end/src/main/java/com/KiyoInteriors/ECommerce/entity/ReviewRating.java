package com.KiyoInteriors.ECommerce.entity;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * This class represents a review and rating given by a user for a product.
 * It contains the following fields:
 * username: a string representing the username of the user who provided the
 * review.
 * rating: an integer representing the rating given to the product, ranging from
 * 1 to 5.
 * review: a string representing the review text provided by the user.
 * The ReviewRating class is used to store user reviews and ratings for
 * products.
 * It allows users to share their opinions and experiences with the products
 * they have used.
 * The username field identifies the user who wrote the review, the rating field
 * indicates
 * the user's satisfaction level with the product (from 1 to 5), and the review
 * field contains
 * the user's comments or feedback regarding the product.
 */
@Data
@Builder
public class ReviewRating {
    private String username;
    private Integer rating;
    private String review;
}
