package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.WishlistRequest;
import com.KiyoInteriors.ECommerce.entity.*;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.repository.WishlistRepository;
import com.KiyoInteriors.ECommerce.DTO.Response.WishlistResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WishlistSevice {
    private final WishlistRepository wishlistRepository;

    public String addItemToWishlist(User user, WishlistRequest wishlistRequest) {
        Wishlist wishlist = wishlistRepository.findWishlistByUserId(user.getId())
                .orElseThrow(() -> new ItemNotFoundException("WIshlist Not Found"));

        for (WishlistItem wishlistItem : wishlist.getWishlistItem().values()) {
            if (wishlistItem.getId().equals(wishlistRequest.getId())) {
                throw new ItemNotFoundException("Item already Exist in wishlist");
            }
        }
        WishlistItem wishlistItem = WishlistItem.builder().id(UUID.randomUUID()
                .toString())
                .productId(wishlistRequest.getProductId())
                .build();
        wishlist.getWishlistItem().put(wishlistItem.getId(), wishlistItem);
        wishlistRepository.save(wishlist);
        return "Item added to wishlist";
    }

    public List<WishlistResponse> displayWishlist(User user) {
        Wishlist wishlist = wishlistRepository.findWishlistByUserId(user.getId())
                .orElseThrow(() -> new ItemNotFoundException("Wishlist for User Id not found"));
        List<WishlistResponse> responseList = new ArrayList<>();
        for (WishlistItem wishlistItem : wishlist.getWishlistItem().values()) {
            responseList.add(WishlistResponse.builder()
                    .id(wishlistItem.getId())
                    .productId(wishlistItem.getProductId())
                    .build());
        }
        return responseList;
    }
}
