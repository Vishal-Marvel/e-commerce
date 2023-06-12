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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/v1")
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

    @GetMapping("/products/{offSet}")
    public ResponseEntity<List<ProductResponse>> searchProducts(
            @RequestParam(value = "category", required = false) List<String> categories,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "priceFrom", required = false) Double priceFrom,
            @RequestParam(value = "priceTo", required = false) Double priceTo,
            @RequestParam(value = "availability", required = false) String availability,
            @RequestParam(value = "color", required = false) List<String> colors,
            @RequestParam(value = "size", required = false) List<String> sizes,
            @RequestParam(value = "priceSort", required = false) String priceSort,
            @RequestParam(value = "ratingSort", required = false) String ratingSort,
            @PathVariable Integer offSet) {
        Pageable pageable = PageRequest.of(offSet, 4);

        Query query = new Query().with(pageable);
//        query.addCriteria(Criteria.where("categories").in("Featured"));

        if (categories != null) {
            List<Category> categoryList = new ArrayList<>();
            for (String category : categories) {
                Category cat = categoryRepository.findByCategory(category)
                        .orElseThrow(() -> new ItemNotFoundException("Category Not Found"));
                categoryList.add(cat);
            }
            System.out.println("categoryList = " + categoryList);
            query.addCriteria(Criteria.where("categories").in(categoryList));
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

        if (colors!= null && !colors.isEmpty()){
            query.addCriteria(Criteria.where("colors").in(colors));
        }
        if (sizes!= null && !sizes.isEmpty()){
            query.addCriteria(Criteria.where("sizes").in(sizes));
        }
        if (priceSort!=null){
            if (priceSort.equals("ASC"))
                query.with(Sort.by(Sort.Direction.ASC, "price"));
            else
                query.with(Sort.by(Sort.Direction.DESC, "price"));
        }
        if (ratingSort!= null){
            query.with(Sort.by(Sort.Direction.DESC, "rating"));
        }

        List<Product> products = mongoOperations.find(query, Product.class);

        return ResponseEntity.ok(new PageImpl<>(products, pageable, products.size())
                .stream()
                .map(ProductResponse::new)
                .toList());
    }


}
