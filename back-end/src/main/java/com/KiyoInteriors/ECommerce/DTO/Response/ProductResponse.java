package com.KiyoInteriors.ECommerce.DTO.Response;

import com.KiyoInteriors.ECommerce.entity.Image;
import com.KiyoInteriors.ECommerce.entity.Product;
import com.KiyoInteriors.ECommerce.entity.ProductSize;
import lombok.Data;

import java.util.List;
/**
 * A class that represents a product response object.
 * It contains the fields and constructor of a product entity.
 */
@Data
public class ProductResponse {
    private String id; // the unique identifier of the product
    private String productName; // the name of the product
    private String productDescription; // the description of the product
    private Double productPrize; // the prize of the product in dollars
    private String category; // the category of the product (e.g. clothing, electronics, etc.)
    private List<Image> productPics; // the list of images of the product
    private String model; // the model of the product (e.g. iPhone 14, Nike Air Max, etc.)
    private List<String> sizes; // the list of available sizes of the product (e.g. S, M, L, etc.)
    private List<String> colors; // the list of available colors of the product (e.g. black, white, red, etc.)

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.productPrize = product.getProductPrize();
        this.category = product.getCategory().toString();
        this.productPics = product.getProductPics();
        this.model = product.getModel();
        this.colors = product.getColors();
        this.sizes = product.getSizes()
                .stream()
                .map(ProductSize::toString)
                .toList();
    }
}
