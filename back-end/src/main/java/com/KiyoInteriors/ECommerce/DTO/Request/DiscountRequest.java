package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DiscountRequest {
    private String couponCode;
    private Integer discountPercentage;
    private List<String> products;
    private Date validity;
}
