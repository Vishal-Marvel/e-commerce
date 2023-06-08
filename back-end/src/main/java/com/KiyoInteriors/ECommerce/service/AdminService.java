package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.CategoryRequest;
import com.KiyoInteriors.ECommerce.DTO.Request.DiscountRequest;
import com.KiyoInteriors.ECommerce.DTO.Request.ProductRequest;
import com.KiyoInteriors.ECommerce.entity.*;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.exceptions.ConstraintException;
import com.KiyoInteriors.ECommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
/**

The "AdminService" class is responsible for handling administrative operations related to categories and products.

It interacts with the "CategoryRepository" and "ProductRepository" to perform CRUD operations on categories and products.

addCategory(CategoryRequest request): Adds a new category based on the provided category request.
*/
@RequiredArgsConstructor
@Service
public class AdminService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final DiscountCouponRepository discountCouponRepository;
    private final CartRepository cartRepository;
    private final ImageService imageService;
    private final UserRepository userRepository;

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
        newProduct.setCostPrice(productRequest.getCostPrice());
        newProduct.setProfitPercentage(productRequest.getProfitPercentage()/100.0);
        newProduct.setPrice(productRequest.getCostPrice() +  productRequest.getCostPrice()*(productRequest.getProfitPercentage()/100.0));
        newProduct.setMRP(productRequest.getMRP());
        newProduct.setProductDescription(productRequest.getProductDescription());
        newProduct.setColors(productRequest.getColors());
        newProduct.setSizes(productRequest.getSizes());
        newProduct.setModel(productRequest.getModel());
        if (productRequest.getProductPics()!=null) {
            List<Image> images = new ArrayList<>();
            for (MultipartFile file : productRequest.getProductPics()) {

                images.add(imageService.compressImage(file));
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
        product.setCostPrice(productRequest.getCostPrice());
        product.setMRP(productRequest.getMRP());
        product.setProfitPercentage(productRequest.getProfitPercentage()/100.0);
        product.setPrice(productRequest.getCostPrice() +  productRequest.getCostPrice()*(productRequest.getProfitPercentage()/100.0));
        product.setProductDescription(productRequest.getProductDescription());
        product.setColors(productRequest.getColors());
        product.setSizes(productRequest.getSizes());
        product.setModel(productRequest.getModel());
        if (productRequest.getProductPics()!=null) {
            List<Image> images = new ArrayList<>();
            for (MultipartFile file : productRequest.getProductPics()) {

                images.add(imageService.compressImage(file));
            }
            product.setProductPics(images);
        }
        productRepository.save(product);
    }

    public void createDiscounts(DiscountRequest discountRequest){
        DiscountCoupon discountCoupon = new DiscountCoupon();
        discountCoupon.setCouponCode(discountRequest.getCouponCode());
        discountCoupon.setDiscountPercentage(discountRequest.getDiscountPercentage()/100.0);
        discountCoupon.setValidity(discountRequest.getValidity());
        for(String id : discountRequest.getProducts()){
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ItemNotFoundException("Product Not Found"));
            product.getCoupons().add(discountRequest.getCouponCode());
            productRepository.save(product);

        }
        discountCouponRepository.save(discountCoupon);
    }
    @Scheduled(cron = "0 0 0 * * ?") // Runs at midnight every day
    public void removeCoupons(){
        for (DiscountCoupon coupon: discountCouponRepository.findAll()){
            if (coupon.getValidity().before(new Date())) {
                for (Product product : productRepository.findAllByCouponsContains(coupon.getCouponCode())) {

                    discountCouponRepository.delete(coupon);
                    product.getCoupons().remove(coupon.getCouponCode());
                    productRepository.save(product);

                }
            }
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void removeInActiveUsers(){
        for (User user: userRepository.findAllByActive(false)){
            if (user.getOTPLimit().before(new Date())){
                cartRepository.delete(cartRepository.findCartByUserId(user.getId()).get());
                userRepository.delete(user);
            }
        }
    }
}
