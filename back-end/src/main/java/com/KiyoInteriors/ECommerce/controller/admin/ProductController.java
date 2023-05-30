package com.KiyoInteriors.ECommerce.controller.admin;

import com.KiyoInteriors.ECommerce.DTO.Request.ProductRequest;
import com.KiyoInteriors.ECommerce.DTO.Response.MiscResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.ProductResponse;
import com.KiyoInteriors.ECommerce.entity.Product;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.repository.ProductRepository;
import com.KiyoInteriors.ECommerce.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/product")
public class ProductController {
    private final AdminService service;
    private final ProductRepository productRepository;
    @PostMapping
    public ResponseEntity<MiscResponse> addProduct(@Valid @ModelAttribute ProductRequest productRequest) throws IOException {
        System.out.println("productRequest = " + productRequest);
        service.addProduct(productRequest);
        return ResponseEntity.ok(MiscResponse.builder().response("Product Added").build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MiscResponse> updateProduct(@Valid @ModelAttribute ProductRequest productRequest, @PathVariable String id) throws IOException {
        service.updateProduct(id, productRequest);
        return ResponseEntity.ok(MiscResponse.builder().response("Product Updated").build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MiscResponse> deleteProduct(@PathVariable String id){
        Product product = productRepository.findById(id).orElseThrow(()-> new ItemNotFoundException("Product Not Found"));
        productRepository.delete(product);
        return ResponseEntity.ok(MiscResponse.builder().response("Product Deleted").build());
    }
    @GetMapping
    public ResponseEntity<ProductRequest> test(){
        return ResponseEntity.ok(new ProductRequest());
    }

}
