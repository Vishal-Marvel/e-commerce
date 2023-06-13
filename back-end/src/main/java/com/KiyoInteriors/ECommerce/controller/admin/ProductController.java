package com.KiyoInteriors.ECommerce.controller.admin;

import com.KiyoInteriors.ECommerce.DTO.Request.DiscountRequest;
import com.KiyoInteriors.ECommerce.DTO.Request.ProductRequest;
import com.KiyoInteriors.ECommerce.DTO.Response.AdminWishListResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.DiscountResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.MiscResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.ProductPreviewResponse;
import com.KiyoInteriors.ECommerce.entity.Product;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.repository.ProductRepository;
import com.KiyoInteriors.ECommerce.service.AdminService;
import com.KiyoInteriors.ECommerce.service.WishlistService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/product")
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")

/**
 * This class is responsible for managing products in the system.
 * It provides methods for adding, updating and deleting products.
 * It also has a test method for returning a sample product request.
 */
public class ProductController {
    private final AdminService service;
    private final ProductRepository productRepository;
    private final WishlistService wishlistService;

    @PostMapping
    public ResponseEntity<MiscResponse> addProduct(@Valid @ModelAttribute ProductRequest productRequest)
            throws IOException {
        service.addProduct(productRequest);
        return ResponseEntity.ok(MiscResponse.builder().response("Product Added").build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MiscResponse> updateProduct(@Valid @ModelAttribute ProductRequest productRequest,
            @PathVariable String id) throws IOException {
        service.updateProduct(id, productRequest);
        return ResponseEntity.ok(MiscResponse.builder().response("Product Updated").build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MiscResponse> deleteProduct(@PathVariable String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product Not Found"));
        productRepository.delete(product);
        return ResponseEntity.ok(MiscResponse.builder().response("Product Deleted").build());
    }

    @PostMapping("/create-discounts")
    public ResponseEntity<MiscResponse> createDiscounts(@RequestBody DiscountRequest discountRequest){
        service.createDiscounts(discountRequest);
        return ResponseEntity.ok(MiscResponse.builder().response("Discounts Created").build());
    }
    @GetMapping("/discounts")
    public ResponseEntity<List<DiscountResponse>> discounts(){
        return ResponseEntity.ok(service.viewDiscounts());
    }

    @GetMapping("/user-wishlist")
    public ResponseEntity<AdminWishListResponse> viewWishlist(){
        return ResponseEntity.ok(wishlistService.viewWishlist());
    }
    @PostMapping("/update-all")
    public String updateAll(@ModelAttribute ProductRequest productRequest){
        service.updateAll(productRequest);
        return "done";
    }

}
