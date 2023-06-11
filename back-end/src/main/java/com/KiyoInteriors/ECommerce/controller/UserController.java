package com.KiyoInteriors.ECommerce.controller;

import com.KiyoInteriors.ECommerce.DTO.Request.*;
import com.KiyoInteriors.ECommerce.DTO.Response.*;
import com.KiyoInteriors.ECommerce.entity.Order;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.exceptions.UserNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CartRepository;
import com.KiyoInteriors.ECommerce.repository.OrderRepository;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import com.KiyoInteriors.ECommerce.service.CartService;
import com.KiyoInteriors.ECommerce.service.OrderService;
import com.KiyoInteriors.ECommerce.service.ProductReviewService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.KiyoInteriors.ECommerce.entity.User;
import org.springframework.http.ResponseEntity;
import com.KiyoInteriors.ECommerce.service.UserService;
import com.KiyoInteriors.ECommerce.service.WishlistService;

import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * A controller class that handles requests related to user and cart operations.
 * It uses the UserService, UserRepository, CartService and CartRepository classes
 * to perform the business logic and data access operations.
 * It also uses the SecurityContextHolder class to get the authenticated user name.
 * A method that returns the user information as a UserResponse object.
 * It uses the userRepository to find the user by username and the userService
 * to convert the user entity to a response object.\
 * A method that updates the user information with the given UserRequest object.
 * It uses the userRepository to find the user by username and the userService
 * to update the user entity with the given request object.
 * @param userDTO a UserRequest object that contains the updated user information.
 * @return a ResponseEntity object with a MiscResponse object as the body and OK status code.
 * @throws UserNotFoundException if the user does not exist in the database.
 * @throws IOException if there is an error in reading or writing the user image file.
 */

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping({ "/user" })
public class UserController
{
    private final UserService userService;
    private final UserRepository userRepository;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final ProductReviewService productReviewService;
    private final WishlistService wishlistService;

    @PostMapping( "/set-profile" )
    public ResponseEntity<MiscResponse> setProfile(@Valid @ModelAttribute SetProfileRequest setProfileRequest) throws IOException, MessagingException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        userService.updateUser(setProfileRequest, user.getId());
        return ResponseEntity.ok(MiscResponse.builder().response("Profile Set").build());
    }

    @PostMapping("/change-email")
    public ResponseEntity<MiscResponse> changeMailId(@RequestBody ChangeMailRequest changeMailRequest) throws MessagingException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        userService.changeMail(user, changeMailRequest);
        return ResponseEntity.ok(MiscResponse.builder().response("Verification Mail Sent").build());

    }

    @GetMapping
    public ResponseEntity<UserResponse> getUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        return ResponseEntity.ok(userService.convertUserToResponse(user));
    }

    @PutMapping
    public ResponseEntity<MiscResponse> updateUser(@Valid @ModelAttribute SetProfileRequest setProfileRequest) throws IOException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        userService.updateUser(setProfileRequest, user.getId());
        return ResponseEntity.ok(MiscResponse.builder().response("User Updated").build());
    }

    @DeleteMapping
    public ResponseEntity<MiscResponse> deleteUser(){
        String name  = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        userRepository.delete(user);
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

    @PostMapping("/order")
    public ResponseEntity<MiscResponse> createOrder(@RequestBody OrderRequest request){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User not Found"));
        orderService.createOrder(user, request);
        return ResponseEntity.ok(MiscResponse.builder().response("Order Created").build());
    }

    @GetMapping("/order")
    public ResponseEntity<List<OrderResponses>> orders(
            @RequestParam(value = "date", required = false) Date date
    ){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User not Found"));
        return ResponseEntity.ok(orderService.displayAllOrder(date, user.getId()));
    }

    @PostMapping("/product/review")
    public ResponseEntity<MiscResponse> giveReview(@RequestBody ReviewRequest review){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User not Found"));
        return ResponseEntity.ok(MiscResponse.builder().response(productReviewService.giveReviewRating(user, review)).build());
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<OrderResponse> orderDetails(@PathVariable String id) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Order Not Found"));
        if (user.getId().equals(order.getUserId()))
            return ResponseEntity.ok(orderService.orderDetails(id));
        else {
            throw new AccessDeniedException("You Dont Have Access");
        }
    }

        @PostMapping("/wishlist")
        public ResponseEntity<MiscResponse> addToWishlist(@RequestBody WishlistRequest wishlistRequest) {
                String name = SecurityContextHolder.getContext().getAuthentication().getName();
                User user = userRepository.findUserByUsername(name)
                                .orElseThrow(() -> new UserNotFoundException("User not Found"));
                return ResponseEntity.ok(MiscResponse.builder()
                                .response(wishlistService.addItemToWishlist(user, wishlistRequest)).build());
        }

        @GetMapping("/wishlist")
        public ResponseEntity<List<ProductPreviewResponse>> showAllOrders() {
                String name = SecurityContextHolder.getContext().getAuthentication().getName();
                User user = userRepository.findUserByUsername(name)
                                .orElseThrow(() -> new UserNotFoundException("User not Found"));
                return ResponseEntity.ok(wishlistService.displayWishlist(user));
        }
}