package com.KiyoInteriors.ECommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private String id;
    private String productId;
    private String size;
    private Integer quantity;
    private String color;

}
