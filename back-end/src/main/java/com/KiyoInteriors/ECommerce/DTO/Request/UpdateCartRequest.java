package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;
/**
 * This class represents a request to update the cart with a new item or modify an existing item.
 * It contains the following fields:
 * - itemId: the unique identifier of the item
 * - size: the size of the item (e.g. S, M, L, XL)
 * - quantity: the number of units of the item to add or update
 * - color: the color of the item (e.g. red, blue, green)
 */
@Data
public class UpdateCartRequest {
    private String itemId;
    private String size;
    private Integer quantity;
    private String color;

}
