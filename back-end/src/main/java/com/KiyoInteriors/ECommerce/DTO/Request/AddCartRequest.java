package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;

@Data
public class AddCartRequest {
    private String productId;
    private String size;
    private Integer quantity;
}
