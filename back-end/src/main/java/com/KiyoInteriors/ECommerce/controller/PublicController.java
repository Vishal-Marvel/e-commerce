package com.KiyoInteriors.ECommerce.controller;

import com.KiyoInteriors.ECommerce.DTO.Response.CategoryResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.ProductResponse;
import com.KiyoInteriors.ECommerce.entity.Category;
import com.KiyoInteriors.ECommerce.entity.Product;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CategoryRepository;
import com.KiyoInteriors.ECommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequiredArgsConstructor
public class PublicController {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

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
            @RequestParam(value = "prizeFrom", required = false) Double prizeFrom,
            @RequestParam(value = "prizeTo", required = false) Double prizeTo){
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
        } else if (prizeFrom!=null && prizeTo !=null) {
            productResponses = productRepository.findProductsByProductPrizeBetween(prizeFrom-0.001, prizeTo+0.001)
                    .stream()
                    .map(ProductResponse::new)
                    .toList();
        }
        return ResponseEntity.ok(productResponses);
                
    }
}
