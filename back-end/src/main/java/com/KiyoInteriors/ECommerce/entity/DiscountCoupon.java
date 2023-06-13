package com.KiyoInteriors.ECommerce.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
/**
 * A class representing a discount coupon.
 * This class holds information about a discount coupon, including its code, discount percentage,
 * applicable products, and validity period.
 */
@Document
@Data
public class DiscountCoupon {
    @Id
    private String couponCode;
    private Double discountPercentage;
    private List<String> products;
    private Date validity;
}
