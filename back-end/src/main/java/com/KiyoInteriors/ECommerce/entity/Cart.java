package com.KiyoInteriors.ECommerce.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Document
public class Cart {
    @Id
    private String id= UUID.randomUUID().toString();
    private User user;
    private List<Product> products = new ArrayList<>();

    public Cart(User user) {
        this.user = user;
    }

}
