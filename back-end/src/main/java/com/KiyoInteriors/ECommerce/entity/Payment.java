package com.KiyoInteriors.ECommerce.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

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
