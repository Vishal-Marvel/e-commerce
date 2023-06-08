package com.KiyoInteriors.ECommerce.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 * This class represents an image file that can be stored and retrieved from a database.
 * It has three fields: fileName, data and contentType.
 * The fileName is the name of the image file, such as "picture.jpg".
 * The data is an array of bytes that contains the binary content of the image file.
 * The contentType is a string that indicates the MIME type of the image file, such as "image/jpeg".
 */
@Data
@Document
public class Order {
    @Id
    private String id = UUID.randomUUID().toString();
    private String userId;
    private Date orderDate;
    private String shippingAddress;
    private String billingAddress;
    private List<OrderItem> orderItems;
    private Double total;
    private String paymentStatus;
    private String orderStatus;
    public Order(String userId) {
        this.userId = userId;
    }
}
