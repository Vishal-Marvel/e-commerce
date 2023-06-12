package com.KiyoInteriors.ECommerce.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * This class represents a product request that contains the information
 * about a product to be added to the online store.
 * It has the following fields:
 * - productName: the name of the product
 * - productDescription: a brief description of the product
 * - productPrize: the price of the product in dollars
 * - category: the category of the product (e.g. clothing, electronics, etc.)
 * - productPics: a list of files that contain the images of the product
 * - model: an optional field that specifies the model of the product (e.g.
 * iPhone 14, Nike Air Max, etc.)
 * - sizes: an optional list of strings that indicate the available sizes of the
 * product (e.g. S, M, L, XL, etc.)
 * - colors: an optional list of strings that indicate the available colors of
 * the product (e.g. black, white, red, etc.)
 */
@Data
public class ProductRequest {
    @NotBlank(message = "Name is necessary")
    private String productName;
    @NotBlank(message = "Description is necessary")
    private String productDescription;
    @NotBlank(message = "Cost Price is necessary")
    private Double costPrice;
    @NotBlank(message = "Profit Percentage is necessary")
    private Integer profitPercentage;
    @NotBlank(message = "MRP is necessary")
    private Double MRP;
    @NotBlank(message = "Category is necessary")
    private List<String> category;
    @NotBlank(message = "Quantity is necessary")
    private Integer quantity;
    private List<MultipartFile> productPics;
    private String model;
    private List<String> sizes;
    private List<String> colors;
}
