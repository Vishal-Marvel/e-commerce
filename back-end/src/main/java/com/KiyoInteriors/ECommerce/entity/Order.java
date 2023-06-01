package com.KiyoInteriors.ECommerce.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Document
public class Order {
    @Id
    private String id = UUID.randomUUID().toString();
    private User user;
    private Date orderDate;
    private String shippingAddress;
    private String billingAddress;
    private List<CartItem> cartItems;
    private Double total;
    private String paymentStatus;
    private String coupon;
    private OrderStatus orderStatus;


}
