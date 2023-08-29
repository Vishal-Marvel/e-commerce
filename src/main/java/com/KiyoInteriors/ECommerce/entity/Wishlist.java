package com.KiyoInteriors.ECommerce.entity;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
/**
 * A class representing a user's wishlist.
 * This class holds information about the wishlist, including its ID, user ID, and the items in the wishlist.
 */
@Data
@Document
public class Wishlist {
    @Id
    private String id = UUID.randomUUID().toString();
    private String userId;
    private List<String> wishlistItems;

    public Wishlist(String id) {
        this.userId = id;
        this.wishlistItems = new ArrayList<>();
    }
}
