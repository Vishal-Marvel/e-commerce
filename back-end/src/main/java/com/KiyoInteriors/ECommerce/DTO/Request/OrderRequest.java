package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private List<String> items;
    private String billingAddress;
    private String coupon;
    private String shippingAddress;

}
