package com.KiyoInteriors.ECommerce.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
public class DiscountCoupon {
    @Id
    private String couponCode;
    private Double discountPercentage;
    private List<String> products;
    private Date validity;
}
