package com.KiyoInteriors.ECommerce.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class DiscountResponse {
    private String couponCode;
    private Date validity;
    private Integer discountPercentage;
    private List<ProductPreviewResponse> products;
}
