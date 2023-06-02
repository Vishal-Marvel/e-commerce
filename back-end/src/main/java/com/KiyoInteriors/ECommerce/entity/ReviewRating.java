package com.KiyoInteriors.ECommerce.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
public class ReviewRating {
    private String username;
    private Integer rating;
    private String review;
}
