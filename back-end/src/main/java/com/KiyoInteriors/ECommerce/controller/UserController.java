package com.KiyoInteriors.ECommerce.controller;

import com.KiyoInteriors.ECommerce.DTO.Request.AddCartRequest;
import com.KiyoInteriors.ECommerce.DTO.Request.UpdateCartRequest;
import com.KiyoInteriors.ECommerce.DTO.Response.CartProductsResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.MiscResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.UserResponse;
import com.KiyoInteriors.ECommerce.exceptions.UserNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CartRepository;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import com.KiyoInteriors.ECommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.KiyoInteriors.ECommerce.DTO.Request.UserRequest;
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
    public ResponseEntity<UserResponse> getUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findUserByUsername(name);
        if (user.isEmpty()){
            throw new UserNotFoundException("User Not Exists");
        }
        return ResponseEntity.ok(userService.convertUserToResponse(user.get()));
    }

    @PutMapping
    public ResponseEntity<MiscResponse> updateUser(@Valid @ModelAttribute UserRequest userDTO) throws IOException {
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
    public ResponseEntity<List<CartProductsResponse>> cart(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        return ResponseEntity.ok(
                cartService.displayCart(user)
        );
    }

    @PostMapping("/cart")
    public ResponseEntity<MiscResponse> addProductToCart(@RequestBody AddCartRequest cartRequest){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User not Found"));
        cartService.addItemToCart(user, cartRequest);
        return ResponseEntity.ok(
                MiscResponse.builder()
                        .response("Product Added To Cart")
                        .build()
        );
    }

    @PutMapping("/cart")
    public ResponseEntity<MiscResponse> updateItem(@RequestBody UpdateCartRequest cartRequest){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User not Found"));
        cartService.updateProduct(user, cartRequest);
        return ResponseEntity.ok(
                MiscResponse.builder()
                        .response("Product Updated in Cart")
                        .build()
        );
    }

    @DeleteMapping("/cart/{itemId}")
    public ResponseEntity<MiscResponse> deleteProductInCart(@PathVariable String itemId){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User not Found"));
        cartService.deleteItemFromCart(user, itemId);
        return ResponseEntity.ok(
                MiscResponse.builder()
                        .response("Product Deleted In Cart")
                        .build()
        );
    }
}