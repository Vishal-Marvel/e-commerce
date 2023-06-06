package com.KiyoInteriors.ECommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * A class that represents an item in a shopping cart.
 * It has the following fields:
 * - id: a unique identifier for the item
 * - productId: the identifier of the product that the item belongs to
 * - size: the size of the product (e.g. small, medium, large)
 * - quantity: the number of units of the product in the item
 * - color: the color of the product (e.g. red, blue, green)
 */
public class OrderItem {
    private String id;
    private String productId;
    private String size;
    private Integer quantity;
    private String color;
    private Double price;

}
