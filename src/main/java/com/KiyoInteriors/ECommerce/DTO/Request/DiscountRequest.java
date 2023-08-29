package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;

import java.util.Date;
import java.util.List;
/**
 * A class representing a Discount request.
 * This class encapsulates the information required to apply a discount to products.
 */
@Data
public class DiscountRequest {
    private String couponCode;
    private Integer discountPercentage;
    private List<String> products;
    private Date validity;
}
