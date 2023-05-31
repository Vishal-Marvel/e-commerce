package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.entity.Cart;
import com.KiyoInteriors.ECommerce.entity.Product;
import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.exceptions.UserNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CartRepository;
import com.KiyoInteriors.ECommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public void addItemToCart(User user, String productId) {
        Cart cart = cartRepository.findCartByUser(user)
                .orElseThrow(() -> new ItemNotFoundException("Cart Not Found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ItemNotFoundException("Product not found"));
        cart.getProducts().add(product);
        cartRepository.save(cart);
    }

    public void deleteProduct(User user, String productId){
        Cart cart = cartRepository.findCartByUser(user)
                .orElseThrow(() -> new ItemNotFoundException("Cart Not Found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ItemNotFoundException("Product not Found"));
        cart.getProducts().remove(product);
        cartRepository.save(cart);

    }
}

