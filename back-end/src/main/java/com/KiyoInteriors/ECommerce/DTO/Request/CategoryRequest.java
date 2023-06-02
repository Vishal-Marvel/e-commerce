package com.KiyoInteriors.ECommerce.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
/**
 * This class represents a request for a category.
 * It has one field: category, which is a String that cannot be blank.
 * It uses the @Data annotation to generate getters, setters, equals, hashCode and toString methods.
 * It uses the @NotBlank annotation to validate that the category field is not null or empty.
 */
@Data
public class CategoryRequest {
    @NotBlank(message= "Category is Necessary")
    private String category;
}
