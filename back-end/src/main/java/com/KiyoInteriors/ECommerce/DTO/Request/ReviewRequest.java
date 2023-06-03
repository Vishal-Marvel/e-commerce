package com.KiyoInteriors.ECommerce.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
/**

This class represents a review request for a product.
It contains the following fields:
productId: a string representing the ID of the product being reviewed.
review: a string representing the review text provided by the user.
rating: an integer representing the rating given to the product, ranging from 1 to 5.
The ReviewRequest class is used to gather information about a product review
in order to add it to the system. It ensures that the rating field is not blank
and requires a valid rating value out of 5.
*/
@Data
public class ReviewRequest
{
    private String productId;
    private String review;
    @NotBlank(message = "Please give a rating out of 5")
    private Integer rating;
}
