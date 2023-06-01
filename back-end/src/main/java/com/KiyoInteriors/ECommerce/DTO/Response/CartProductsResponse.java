package com.KiyoInteriors.ECommerce.DTO.Response;

import com.KiyoInteriors.ECommerce.entity.Image;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartProductsResponse {
    private String itemId;
    private String name;
    private Double prize;
    private Image image;
    private String size;
    private String color;
    private String model;
    private Integer quantity;


}
