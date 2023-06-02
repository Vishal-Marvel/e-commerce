package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;
/**
 * This class represents a request to add a product to the shopping cart.
 * It contains the following fields:
 * - productId: the unique identifier of the product
 * - size: the size of the product (e.g. S, M, L, XL)
 * - quantity: the number of units to add
 * - color: the color of the product (e.g. red, blue, green)
 */
@Data 
public class AddCartRequest {
    private  String productId;
    private  String size;
    private  Integer quantity;
    private  String color;
}
