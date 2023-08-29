package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.AddCartRequest;
import com.KiyoInteriors.ECommerce.DTO.Request.UpdateCartRequest;
import com.KiyoInteriors.ECommerce.DTO.Response.CartProductsResponse;
import com.KiyoInteriors.ECommerce.entity.*;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CartRepository;
import com.KiyoInteriors.ECommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * 
 * The "CartService" class is responsible for managing the user's cart,
 * including adding items, updating quantities and attributes, deleting items,
 * and displaying the cart.
 * It interacts with the "CartRepository" and "ProductRepository" to perform
 * these operations.
 * addItemToCart(User user, AddCartRequest request): Adds an item to the user's
 * cart.
 */

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public void addItemToCart(User user, AddCartRequest request) {
        Cart cart = cartRepository.findCartByUserId(user.getId())
                .orElseThrow(() -> new ItemNotFoundException("Cart Not Found"));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ItemNotFoundException("Product Not Found"));

        if (request.getQuantity() > product.getQuantity()) {
            throw new ItemNotFoundException("Quantity insufficient");
        }
        for (CartItem item : cart.getCartItem().values()){
            if (item.getProductId().equals(request.getProductId())
            && Objects.equals(item.getSize(),request.getSize())
            && Objects.equals(item.getColor(),request.getColor())){
                item.setQuantity(item.getQuantity()+1);
                cartRepository.save(cart);
                return;
            }
        }

        CartItem cartItemParameter = CartItem.builder()
                .productId(product.getId())
                .id(UUID.randomUUID().toString())
                .size(request.getSize())
                .color(request.getColor())
                .quantity(request.getQuantity())
                .build();
        cart.getCartItem().put(cartItemParameter.getId(), cartItemParameter);
        cartRepository.save(cart);
    }

    public void updateProduct(User user, UpdateCartRequest request) {
        Cart cart = cartRepository.findCartByUserId(user.getId())
                .orElseThrow(() -> new ItemNotFoundException("Cart Not Found"));
        Product product = productRepository.findById(request.getItemId())
                .orElseThrow(() -> new ItemNotFoundException("Requested Product not found"));
        if (!cart.getCartItem().containsKey(request.getItemId())) {
            throw new ItemNotFoundException("Item Not Found");
        }
        CartItem item = cart.getCartItem().get(request.getItemId());
        item.setColor(request.getColor());

        item.setQuantity(request.getQuantity());

        item.setSize(request.getSize());
        if (item.getQuantity() > product.getQuantity()) {
            throw new ItemNotFoundException("Quantity insufficient");
        }
        cartRepository.save(cart);
    }

    public void deleteItemFromCart(User user, String orderId) {
        Cart cart = cartRepository.findCartByUserId(user.getId())
                .orElseThrow(() -> new ItemNotFoundException("Cart Not Found"));

        cart.getCartItem().remove(orderId);
        cartRepository.save(cart);

    }

    public List<CartProductsResponse> displayCart(User user) {
        Cart cart = cartRepository.findCartByUserId(user.getId())
                .orElseThrow(() -> new ItemNotFoundException("Cart Not Found"));
        List<CartProductsResponse> cartProductsResponseList = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItem().values()) {
            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new ItemNotFoundException("Product Not Found"));
            cartProductsResponseList.add(CartProductsResponse.builder()
                    .itemId(cartItem.getId())
                    .name(product.getProductName())
                    .price(product.getPrice())
                    .image(product.getProductPics().get(0))
                    .model(product.getModel())
                    .color(cartItem.getColor())
                    .quantity(cartItem.getQuantity())
                    .size(cartItem.getSize())
                    .build());
        }
        return cartProductsResponseList;
    }
}
