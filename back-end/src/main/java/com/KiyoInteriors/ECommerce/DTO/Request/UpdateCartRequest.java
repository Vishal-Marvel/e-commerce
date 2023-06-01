package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;

@Data
public class UpdateCartRequest {
    private String itemId;
    private String size;
    private Integer quantity;
    private String color;

}
