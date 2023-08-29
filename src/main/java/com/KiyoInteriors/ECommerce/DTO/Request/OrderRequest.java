package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;

import java.util.List;

/**

This class represents a request to create an order.
It contains the following fields:
items: a list of strings representing the IDs or names of the items being ordered.
billingAddress: a string representing the billing address for the order.
coupon: a string representing the coupon code applied to the order, if any.
shippingAddress: a string representing the shipping address for the order.
The OrderRequest class is used to gather information needed to create a new order in an
online store. 
*/
@Data
public class OrderRequest {
    private List<String> items;
    private String billingAddress;
    private String coupon;
    private String shippingAddress;

}
