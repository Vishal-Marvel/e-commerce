package com.KiyoInteriors.ECommerce.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;
/**
 * This class represents a payment entity that stores the details of a payment made by a customer for an order.
 */
@Data
@Document
public class Payment {
    @Id
    private String id = UUID.randomUUID().toString();
    private Order order;
    private Date paymentDate;
    private String paymentMethod;
    private Double paymentAmt;
    private String currency;
    private String paymentStatus;
    private String transactionId;
    private String paymentGatewayResponse;
    private String billingAddress;



}
