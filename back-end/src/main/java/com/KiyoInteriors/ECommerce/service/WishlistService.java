package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.WishlistRequest;
import com.KiyoInteriors.ECommerce.DTO.Response.AdminWishListResponse;
import com.KiyoInteriors.ECommerce.entity.*;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.repository.ProductRepository;
import com.KiyoInteriors.ECommerce.repository.WishlistRepository;
import com.KiyoInteriors.ECommerce.DTO.Response.ProductPreviewResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;

    public String addItemToWishlist(User user, WishlistRequest wishlistRequest) {
        Wishlist wishlist = wishlistRepository.findWishlistByUserId(user.getId())
                .orElseThrow(() -> new ItemNotFoundException("Wishlist Not Found"));
        Product product = productRepository.findById(wishlistRequest.getProductId())
                .orElseThrow(() -> new ItemNotFoundException("Product Not Found"));

        if (!wishlist.getWishlistItems().contains(wishlistRequest.getProductId())){
            wishlist.getWishlistItems().add(wishlistRequest.getProductId());
        }
        wishlistRepository.save(wishlist);
        return "Item added to wishlist";
    }

    public List<ProductPreviewResponse> displayWishlist(User user) {
        Wishlist wishlist = wishlistRepository.findWishlistByUserId(user.getId())
                .orElseThrow(() -> new ItemNotFoundException("Wishlist for User Id not found"));
        List<ProductPreviewResponse> responseList = new ArrayList<>();
        for (String wishlistItem : wishlist.getWishlistItems()) {
            Product product = productRepository.findById(wishlistItem)
                    .orElseThrow(() -> new ItemNotFoundException("Product Not Found"));
            responseList.add(ProductPreviewResponse.builder()
                            .productName(product.getProductName())
                            .image(product.getProductPics().get(0))
                            .category(product.getCategories())
                            .productId(wishlistItem)
                            .build());
        }
        return responseList;
    }

    public AdminWishListResponse viewWishlist() {
        List<Wishlist> wishlists = wishlistRepository.findAll();
        Map<ProductPreviewResponse , Integer> products = new HashMap<>();
        for (Wishlist wishlist: wishlists){
            for (String item: wishlist.getWishlistItems()){
                Product product = productRepository.findById(item)
                        .orElseThrow(()->new ItemNotFoundException("Product Not Found"));
                ProductPreviewResponse tempProduct = ProductPreviewResponse.builder()
                        .productId(product.getId())
                        .productName(product.getProductName())
                        .category(product.getCategories())
                        .image(product.getProductPics().get(0)).build();
                products.put(tempProduct, products.getOrDefault(tempProduct, 0)+1);
            }
        }
        return AdminWishListResponse.builder().products(products).build();
    }
}
