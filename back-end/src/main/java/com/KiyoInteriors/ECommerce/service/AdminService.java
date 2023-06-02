package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.CategoryRequest;
import com.KiyoInteriors.ECommerce.DTO.Request.ProductRequest;
import com.KiyoInteriors.ECommerce.entity.Category;
import com.KiyoInteriors.ECommerce.entity.Image;
import com.KiyoInteriors.ECommerce.entity.Product;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.exceptions.ConstraintException;
import com.KiyoInteriors.ECommerce.repository.CategoryRepository;
import com.KiyoInteriors.ECommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public void addCategory(CategoryRequest request){
        Optional<Category> tempCategory = categoryRepository.findByCategory(request.getCategory());
        if(tempCategory.isPresent()){
            throw new ConstraintException("Category Already Exists");
        }
        Category category = new Category();
        category.setCategory(request.getCategory());
        categoryRepository.save(category);
    }

    public void updateCategory(String name, CategoryRequest request){
        Category category = categoryRepository.findByCategory(name)
                .orElseThrow(()->new ItemNotFoundException("Category Not Found"));
        category.setCategory(request.getCategory());
        categoryRepository.save(category);
    }

    public void addProduct(ProductRequest productRequest) throws IOException {
        Product newProduct = new Product();
        newProduct.setCategory(categoryRepository
                .findByCategory(productRequest
                        .getCategory())
                .orElseThrow(()->new ItemNotFoundException("Category Not Found")));
        newProduct.setProductName(productRequest.getProductName());
        newProduct.setProductPrize(productRequest.getProductPrize());
        newProduct.setProductDescription(productRequest.getProductDescription());
        newProduct.setColors(productRequest.getColors());
        newProduct.setSizes(productRequest.getSizes());
        newProduct.setModel(productRequest.getModel());
        if (productRequest.getProductPics()!=null) {
            List<Image> images = new ArrayList<>();
            for (MultipartFile file : productRequest.getProductPics()) {
                Image image = Image.builder()
                        .data(file.getBytes())
                        .fileName(file.getOriginalFilename())
                        .contentType(file.getContentType())
                        .build();
                images.add(image);
            }
            newProduct.setProductPics(images);
        }
        productRepository.save(newProduct);
    }

    public void updateProduct(String id, ProductRequest productRequest) throws IOException{
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product Not Found"));
        product.setCategory(categoryRepository
                .findByCategory(productRequest
                        .getCategory())
                .orElseThrow(()->new ItemNotFoundException("Category Not Found")));
        product.setProductName(productRequest.getProductName());
        product.setProductPrize(productRequest.getProductPrize());
        product.setProductDescription(productRequest.getProductDescription());
        product.setColors(productRequest.getColors());
        product.setSizes(productRequest.getSizes());
        product.setModel(productRequest.getModel());
        if (productRequest.getProductPics()!=null) {
            List<Image> images = new ArrayList<>();
            for (MultipartFile file : productRequest.getProductPics()) {
                Image image = Image.builder()
                        .data(file.getBytes())
                        .fileName(file.getOriginalFilename())
                        .contentType(file.getContentType())
                        .build();
                images.add(image);
            }
            product.setProductPics(images);
        }
        productRepository.save(product);
    }
}
