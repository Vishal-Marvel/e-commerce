package com.KiyoInteriors.ECommerce.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Data
@Document
public class Cart {
    @Id
    private String id= UUID.randomUUID().toString();
    private User user;
    private Map<String, CartItem> cartItem;
    public Cart(User user) {
        this.user = user;
        this.cartItem = new HashMap<>();
    }

}