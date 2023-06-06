package com.KiyoInteriors.ECommerce.controller;

import com.KiyoInteriors.ECommerce.DTO.Response.CategoryResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.ProductResponse;
import com.KiyoInteriors.ECommerce.entity.*;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.exceptions.UserNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CategoryRepository;
import com.KiyoInteriors.ECommerce.repository.OrderRepository;
import com.KiyoInteriors.ECommerce.repository.ProductRepository;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import com.KiyoInteriors.ECommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequiredArgsConstructor
/**
 * This class provides methods for accessing and manipulating public data.
 * It uses the CategoryRepository and ProductRepository interfaces to perform CRUD operations on the database.
 * It also supports searching products by category, name, or price range.
 */
public class PublicController {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderService orderService;

    @GetMapping("/category")
    public ResponseEntity<List<CategoryResponse>> categories(){
        return ResponseEntity.ok(
                categoryRepository.findAll()
                        .stream()
                        .map(cat->CategoryResponse.builder()
                                .categoryName(cat.getCategory())
                                .build())
                        .toList()
        );
    }
    @GetMapping("/category/{name}")
    public ResponseEntity<CategoryResponse> category(@PathVariable String name){
        Category category = categoryRepository.findByCategory(name).orElseThrow(() -> new ItemNotFoundException("Category Not Found"));
        return ResponseEntity.ok(
                CategoryResponse.builder()
                        .categoryName(category.getCategory())
                        .build()
        );
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductResponse>> products(){

        return ResponseEntity.ok(
                productRepository.findAll()
                        .stream()
                        .map(ProductResponse::new)
                        .toList()
        );
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> product(@PathVariable String id){
        Product product = productRepository.findById(id).orElseThrow(()-> new ItemNotFoundException("Product Not Found"));
        return ResponseEntity.ok(new ProductResponse(product));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(
            @RequestParam(value = "category",required = false) String category,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "priceFrom", required = false) Double priceFrom,
            @RequestParam(value = "priceTo", required = false) Double priceTo){
        List<ProductResponse> productResponses = new ArrayList<>();

        if (category!=null && name != null) {
            productResponses = productRepository.findProductsByProductNameLikeIgnoreCaseAndCategory(
                            name,
                            categoryRepository.findByCategory(category)
                                    .orElseThrow(() -> new ItemNotFoundException("Category Not Found"))
                    ).stream()
                    .map(ProductResponse::new)
                    .toList();
        } else if (name!=null) {
            productResponses = productRepository.findProductsByProductNameLikeIgnoreCase(
                            name).stream()
                    .map(ProductResponse::new)
                    .toList();
        } else if (category!=null) {
            productResponses = productRepository.findProductsByCategory(
                            categoryRepository.findByCategory(category)
                                    .orElseThrow(() -> new ItemNotFoundException("Category Not Found"))
                    ).stream()
                    .map(ProductResponse::new)
                    .toList();
        } else if (priceFrom!=null && priceTo !=null) {
            productResponses = productRepository.findProductsByProductPriceBetween(priceFrom-0.001, priceTo+0.001)
                    .stream()
                    .map(ProductResponse::new)
                    .toList();
        }
        return ResponseEntity.ok(productResponses);
                
    }
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/order/{id}")
    public ResponseEntity<String> orderDetails(@PathVariable String id){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Order Not Found"));
        if (user.getRole() == UserRole.ROLE_ADMIN || user.getId().equals(order.getUserId()))
            return ResponseEntity.ok(id);
        else{
            throw new AccessDeniedException("You Dont Have Access");
        }
    }
}
