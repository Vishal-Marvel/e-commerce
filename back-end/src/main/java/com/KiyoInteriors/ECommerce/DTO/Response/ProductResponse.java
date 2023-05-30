package com.KiyoInteriors.ECommerce.DTO.Response;

import com.KiyoInteriors.ECommerce.entity.Image;
import com.KiyoInteriors.ECommerce.entity.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private String id;
    private String productName;
    private String productDescription;
    private Double productPrize;
    private String category;
    private List<Image> productPics;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.productPrize = product.getProductPrize();
        this.category = product.getCategory().toString();
        this.productPics = product.getProductPics();
    }
}
