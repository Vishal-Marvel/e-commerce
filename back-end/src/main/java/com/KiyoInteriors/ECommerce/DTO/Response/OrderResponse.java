package com.KiyoInteriors.ECommerce.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder

public class OrderResponse {
    private String orderId;
    private Double amount;
    private Date orderDate;
    private String orderStatus;
    private String buildingAddress;
    private String shippingAddress;
    private String paymentMethod;
    private List<CartProductsResponse> items;

}
