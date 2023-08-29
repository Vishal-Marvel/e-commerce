package com.KiyoInteriors.ECommerce.DTO.Response;

import lombok.Builder;
import lombok.Data;
/**
 * This class represents a category response object that contains the name of a category.
 * It uses the @Data and @Builder annotations from Lombok to generate getters, setters, constructors, and builders for the class fields.
 */
@Data
@Builder
public class CategoryResponse {
    private String categoryName;
}
