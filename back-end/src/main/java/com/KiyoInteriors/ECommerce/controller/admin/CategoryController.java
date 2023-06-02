package com.KiyoInteriors.ECommerce.controller.admin;

import com.KiyoInteriors.ECommerce.DTO.Request.CategoryRequest;
import com.KiyoInteriors.ECommerce.DTO.Response.CategoryResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.MiscResponse;
import com.KiyoInteriors.ECommerce.entity.Category;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CategoryRepository;
import com.KiyoInteriors.ECommerce.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/category")
/**
 * This class is responsible for managing categories of products.
 * It provides methods to add, update and delete categories using a repository and a service layer.
 */
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final AdminService service;

    @PostMapping
    public ResponseEntity<MiscResponse> addCategory(@RequestBody CategoryRequest categoryRequest){
        service.addCategory(categoryRequest);
        return ResponseEntity.ok(MiscResponse.builder()
                .response("Category Added")
                .build());
    }
    @PutMapping("/{name}")
    public ResponseEntity<MiscResponse> updateCategory(@RequestBody CategoryRequest categoryRequest, @PathVariable String name){
        service.updateCategory(name, categoryRequest);
        return ResponseEntity.ok(MiscResponse.builder()
                .response("Category Updated")
                .build());
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<MiscResponse> deleteCategory(@PathVariable String name){
        Category category = categoryRepository.findByCategory(name)
                .orElseThrow(()->new ItemNotFoundException("Category Not Found"));
        categoryRepository.delete(category);
        return ResponseEntity.ok(
                MiscResponse.builder()
                        .response("Category Deleted")
                        .build()
        );
    }
}
