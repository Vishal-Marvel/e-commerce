package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.AddCartRequest;
import com.KiyoInteriors.ECommerce.DTO.Response.CartProductsResponse;
import com.KiyoInteriors.ECommerce.entity.*;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CartRepository;
import com.KiyoInteriors.ECommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public void updateCart(User user, AddCartRequest request) {
        Cart cart = cartRepository.findCartByUser(user)
                .orElseThrow(() -> new ItemNotFoundException("Cart Not Found"));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(()-> new ItemNotFoundException("Cart Not Found"));
        Map<String, SizeQuantity> products = cart.getCartItem();

        SizeQuantity sizeQuantity =  SizeQuantity.builder()
                .size(ProductSize.valueOf(request.getSize()))
                .quantity(request.getQuantity())
                .build();
        products.put(request.getProductId(), sizeQuantity);
        cartRepository.save(cart);
    }

    public void deleteItemFromCart(User user, String productId){
        Cart cart = cartRepository.findCartByUser(user)
                .orElseThrow(() -> new ItemNotFoundException("Cart Not Found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ItemNotFoundException("Cart Not Found"));
        cart.getCartItem().remove(productId);
        cartRepository.save(cart);

    }

    public List<CartProductsResponse> displayCart(User user) {
        Cart cart = cartRepository.findCartByUser(user)
                .orElseThrow(()-> new ItemNotFoundException("Cart Not Found"));
        List<CartProductsResponse> cartProductsResponseList = new ArrayList<>();
        for(String productId: cart.getCartItem().keySet()){
            Product product = productRepository.findById(productId)
                    .orElseThrow(()-> new ItemNotFoundException("Cart Not Found"));
            cartProductsResponseList.add(CartProductsResponse.builder()
                    .id(product.getId())
                    .name(product.getProductName())
                    .prize(product.getProductPrize())
                    .image(product.getProductPics().get(0))
                    .quantity(cart.getCartItem().get(productId).getQuantity())
                    .size(cart.getCartItem().get(productId).getSize().toString())
                    .build());
        }
        return cartProductsResponseList;
    }
}

