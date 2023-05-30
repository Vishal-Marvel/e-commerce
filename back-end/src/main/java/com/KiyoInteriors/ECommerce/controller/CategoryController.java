package com.KiyoInteriors.ECommerce.controller;

import com.KiyoInteriors.ECommerce.DTO.Request.CategoryRequest;
import com.KiyoInteriors.ECommerce.DTO.Response.CategoryResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.MiscResponse;
import com.KiyoInteriors.ECommerce.entity.Category;
import com.KiyoInteriors.ECommerce.exceptions.CategoryNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CategoryRepository;
import com.KiyoInteriors.ECommerce.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/category")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final AdminService service;
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> categories(){
        return ResponseEntity.ok(
                categoryRepository.findAll()
                        .stream()
                        .map(cat->CategoryResponse.builder()
                                .categoryName(cat.getCategory())
                                .id(cat.getId())
                                .build())
                        .toList()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> category(@PathVariable String id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category Not Found"));
        return ResponseEntity.ok(
            CategoryResponse.builder()
                    .id(category.getId())
                    .categoryName(category.getCategory())
                    .build()
        );
    }
    @PostMapping
    public ResponseEntity<MiscResponse> addCategory(@RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.ok(MiscResponse.builder()
                .response(service.addCategory(categoryRequest))
                .build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<MiscResponse> updateCategory(@RequestBody CategoryRequest categoryRequest, @PathVariable String id){
        return ResponseEntity.ok(MiscResponse.builder()
                .response(service.updateCategory(id, categoryRequest))
                .build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MiscResponse> deleteCategory(@PathVariable String id){
        Category category = categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category Not Found"));
        categoryRepository.delete(category);
        return ResponseEntity.ok(
                MiscResponse.builder()
                        .response("Category Deleted")
                        .build()
        );
    }
}
