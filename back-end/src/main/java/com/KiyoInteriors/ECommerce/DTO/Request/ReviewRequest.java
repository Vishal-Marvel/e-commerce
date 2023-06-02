package com.KiyoInteriors.ECommerce.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewRequest
{
    private String productId;
    private String review;
    @NotBlank(message = "Please give a rating out of 5")
    private Integer rating;
}
