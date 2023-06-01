package com.KiyoInteriors.ECommerce.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductRequest {
    @NotBlank(message = "Name is necessary")
    private String productName;
    @NotBlank(message = "Description is necessary")
    private String productDescription;
    @NotBlank(message = "Prize is necessary")
    private Double productPrize;
    @NotBlank(message = "Category is necessary")
    private String category;
    private List<MultipartFile> productPics;
    private String model;
    private List<String> sizes;
    private List<String> colors;
}
