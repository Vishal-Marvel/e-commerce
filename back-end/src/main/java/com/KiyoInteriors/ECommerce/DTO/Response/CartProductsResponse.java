package com.KiyoInteriors.ECommerce.DTO.Response;

import com.KiyoInteriors.ECommerce.entity.Image;
import lombok.Builder;
import lombok.Data;
/**
 * This class represents a response object for a cart product.
 * It uses the @Data and @Builder annotations from Lombok to generate getters, setters, constructors, and builders.
 * It has the following fields:
 * - itemId: a unique identifier for the product
 * - name: the name of the product
 * - prize: the price of the product
 * - image: an image of the product
 * - size: the size of the product
 * - color: the color of the product
 * - model: the model of the product
 * - quantity: the quantity of the product in the cart
 */
@Data
@Builder
public class CartProductsResponse {
    private String itemId;
    private String name;
    private Double price;
    private Image image;
    private String size;
    private String color;
    private String model;
    private Integer quantity;


}
