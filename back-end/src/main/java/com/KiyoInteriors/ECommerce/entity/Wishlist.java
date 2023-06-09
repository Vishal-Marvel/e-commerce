package com.KiyoInteriors.ECommerce.entity;

import java.util.HashMap;
import java.lang.String;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Wishlist {
    @Id
    private String id = UUID.randomUUID().toString();
    private String userId;
    private Map<String, WishlistItem> wishlistItem;

    public Wishlist(String id) {
        this.userId = id;
        this.wishlistItem = new HashMap<>();
    }
}
