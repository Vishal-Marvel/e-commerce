package com.KiyoInteriors.ECommerce.controller;

import com.KiyoInteriors.ECommerce.DTO.Response.CategoryResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.ImageResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.OrderResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.ProductResponse;
import com.KiyoInteriors.ECommerce.entity.*;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.exceptions.UserNotFoundException;
import com.KiyoInteriors.ECommerce.repository.*;
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
    private final MongoOperations mongoOperations;
    private final RawImageRepository rawImageRepository;

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
    private int countMatchingCategories(List<String> categories, List<String> targetCategories) {
        int count = 0;
        for (String category : categories) {
            if (targetCategories.contains(category)) {
                count++;
            }
        }
        return count;
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<ImageResponse> getImage(@PathVariable String id){
        return ResponseEntity.ok(ImageResponse.builder()
                .image(rawImageRepository.findById(id)
                        .orElseThrow(()->new ItemNotFoundException("Image Not Found"))
                        .getData())
                .build());
    }

    @GetMapping("/products/{offSet}/{size}")
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
            @PathVariable Integer offSet, @PathVariable Integer size) {
        Pageable pageable = PageRequest.of(offSet, size);

        Query query = new Query();

        if (categories != null) {
            query.addCriteria(Criteria.where("categories").in(categories));
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
        List<String> targetCategories = new ArrayList<>();
        targetCategories.add("Featured");
        targetCategories.add("On Sale");
        targetCategories.add("New Coming");
        products.sort((p1, p2) -> {
            int countP1 = countMatchingCategories(p1.getCategories(), targetCategories);
            int countP2 = countMatchingCategories(p2.getCategories(), targetCategories);
            return Integer.compare(countP2, countP1); // Sort in descending order
        });
        List<Product> paginatedProducts = products.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .toList();

        return ResponseEntity.ok(new PageImpl<>(paginatedProducts, pageable, products.size())
                .stream()
                .map(ProductResponse::new)
                .toList());
    }


}
