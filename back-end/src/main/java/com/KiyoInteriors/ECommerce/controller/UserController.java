package com.KiyoInteriors.ECommerce.controller;

import com.KiyoInteriors.ECommerce.DTO.Response.MiscResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.ProductResponse;
import com.KiyoInteriors.ECommerce.entity.Cart;
import com.KiyoInteriors.ECommerce.entity.Product;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.exceptions.UserNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CartRepository;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import com.KiyoInteriors.ECommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.KiyoInteriors.ECommerce.DTO.Request.UserDTO;
import com.KiyoInteriors.ECommerce.entity.User;
import org.springframework.http.ResponseEntity;
import com.KiyoInteriors.ECommerce.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping({ "/user" })
public class UserController
{
    private final UserService userService;
    private final UserRepository userRepository;
    private final CartService cartService;
    private final CartRepository cartRepository;

    @GetMapping
    public ResponseEntity<User> getUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findUserByUsername(name);
        if (user.isEmpty()){
            throw new UserNotFoundException("User Not Exists");
        }
        return ResponseEntity.ok(user.get());
    }

    @PutMapping
    public ResponseEntity<MiscResponse> updateUser(@Valid @ModelAttribute UserDTO userDTO) throws IOException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findUserByUsername(name);
        if (user.isEmpty()){
            throw new UserNotFoundException("User Not Exists");
        }
        userService.updateUser(userDTO, user.get().getId());
        return ResponseEntity.ok(MiscResponse.builder().response("User Updated").build());
    }

    @DeleteMapping
    public ResponseEntity<MiscResponse> deleteUser(){
        String name  = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findUserByUsername( name);
        if (user.isEmpty()){
            throw new UserNotFoundException("User Not Exists");
        }
        userRepository.delete(user.get());
        return ResponseEntity.ok(MiscResponse.builder().response("User Deleted").build());
    }
    @GetMapping("/cart")
    public ResponseEntity<List<ProductResponse>> cart(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        Cart cart = cartRepository.findCartByUser(user)
                .orElseThrow(() -> new ItemNotFoundException("Cart Not Found"));
        return ResponseEntity.ok(
                cart.getProducts()
                        .stream()
                        .map(ProductResponse::new)
                        .toList()
        );
    }

    @PostMapping("/cart/{productId}")
    public ResponseEntity<MiscResponse> addProductToCart(@PathVariable String productId){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User not Found"));
        cartService.addItemToCart(user, productId);
        return ResponseEntity.ok(
                MiscResponse.builder()
                        .response("Product Added To Cart")
                        .build()
        );

    }

    @DeleteMapping("/cart/{productId}")
    public ResponseEntity<MiscResponse> deleteProductInCart(@PathVariable String productId){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User not Found"));
        cartService.deleteProduct(user, productId);
        return ResponseEntity.ok(
                MiscResponse.builder()
                        .response("Product Deleted In Cart")
                        .build()
        );

    }

}