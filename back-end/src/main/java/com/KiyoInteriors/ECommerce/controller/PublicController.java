package com.KiyoInteriors.ECommerce.controller;

import com.KiyoInteriors.ECommerce.DTO.Response.CategoryResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.OrderResponse;
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
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
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
    private final MongoOperations mongoOperations;

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

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> product(@PathVariable String id){
        Product product = productRepository.findById(id).orElseThrow(()-> new ItemNotFoundException("Product Not Found"));
        return ResponseEntity.ok(new ProductResponse(product));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> searchProducts(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "priceFrom", required = false) Double priceFrom,
            @RequestParam(value = "priceTo", required = false) Double priceTo,
            @RequestParam(value = "availability", required = false) String availability,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "size", required = false) String size) {

        Query query = new Query();

        if (category != null) {
            Category category1 = categoryRepository.findByCategory(category)
                            .orElseThrow(()->new ItemNotFoundException("Category Not Found"));
            query.addCriteria(Criteria.where("category").is(category1));
        }

        if (name != null) {
            query.addCriteria(Criteria.where("productName").regex(name, "i"));
        }

        if (priceFrom != null && priceTo != null) {
            query.addCriteria(Criteria.where("price").gte(priceFrom).lte(priceTo));
        }

        if (availability != null){
            if (availability.equals("true"))
                query.addCriteria(Criteria.where("quantity").gte(0));
            else
                query.addCriteria(Criteria.where("quantity").is(0));
        }

        if (color!= null){
            query.addCriteria(Criteria.where("color").is(color));
        }
        if (size!= null){
            query.addCriteria(Criteria.where("size").is(size));
        }

        List<Product> products = mongoOperations.find(query, Product.class);
        return ResponseEntity.ok(products
                .stream()
                .map(ProductResponse::new)
                .toList());
    }


}
