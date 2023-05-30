package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.CategoryRequest;
import com.KiyoInteriors.ECommerce.entity.Category;
import com.KiyoInteriors.ECommerce.exceptions.CategoryNotFoundException;
import com.KiyoInteriors.ECommerce.exceptions.ConstraintException;
import com.KiyoInteriors.ECommerce.repository.CategoryRepository;
import com.KiyoInteriors.ECommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public String addCategory(CategoryRequest request){
        Optional<Category> tempCategory = categoryRepository.findByCategory(request.getCategory());
        if(tempCategory.isPresent()){
            throw new ConstraintException("Category Already Exists");
        }
        Category category = new Category();
        category.setCategory(request.getCategory());
        categoryRepository.save(category);
        return "Category Saved";
    }

    public String updateCategory(String id, CategoryRequest request){
        Category category = categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category Not Found"));
        category.setCategory(request.getCategory());
        categoryRepository.save(category);
        return "Category Updated";
    }

}
