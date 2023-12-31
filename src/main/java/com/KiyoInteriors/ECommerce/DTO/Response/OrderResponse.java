package com.KiyoInteriors.ECommerce.DTO.Response;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;
/**
 * A class representing an Order Response.
 * This class encapsulates the information related to an order, including its ID, amount, date, status,
 * building address, shipping address, payment method, and the list of items in the order.
 */
@Data
@AllArgsConstructor

public class OrderResponse {
    private String orderId;
    private Double amount;
    private String orderNum;
    private Date orderDate;
    private String orderStatus;
    private String buildingAddress;
    private String shippingAddress;
    private String paymentMethod;
    private List<CartProductsResponse> items;

}
