package com.KiyoInteriors.ECommerce.DTO.Request;

import com.KiyoInteriors.ECommerce.entity.Image;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    @NotBlank(message = "Name is necessary")
    private String productName;
    @NotBlank(message = "Description is necessary")
    private String productDescription;
    @NotBlank(message = "Prize is necessary")
    private Double productPrize;
    @NotBlank(message = "Category is necessary")
    private String category;
    private List<Image> productPics;
}
