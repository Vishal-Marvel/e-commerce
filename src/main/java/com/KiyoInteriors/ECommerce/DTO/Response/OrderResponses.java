package com.KiyoInteriors.ECommerce.DTO.Response;

import lombok.Data;

import java.util.Date;
import java.util.List;
/**
 * A class representing an Order Response.
 * This class encapsulates the information related to an order, including its ID, amount, date, status,
 * and the list of items in the order.
 */
@Data

public class OrderResponses {
    private String orderId;
    private String orderNum;
    private Double amount;
    private Date orderDate;
    private String orderStatus;
    private List<CartProductsResponse> items;

}
