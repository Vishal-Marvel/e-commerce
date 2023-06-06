package com.KiyoInteriors.ECommerce.DTO.Response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data

public class OrderResponses {
    private String orderId;
    private Double amount;
    private Date orderDate;
    private String orderStatus;
    private List<CartProductsResponse> items;

}
