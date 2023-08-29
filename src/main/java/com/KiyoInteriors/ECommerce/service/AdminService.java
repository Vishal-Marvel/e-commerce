package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.CategoryRequest;
import com.KiyoInteriors.ECommerce.DTO.Request.DiscountRequest;
import com.KiyoInteriors.ECommerce.DTO.Request.ProductRequest;
import com.KiyoInteriors.ECommerce.DTO.Response.AdminProfitResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.DiscountResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.ProductPreviewResponse;
import com.KiyoInteriors.ECommerce.entity.*;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.exceptions.ConstraintException;
import com.KiyoInteriors.ECommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * The "AdminService" class is responsible for handling administrative
 * operations related to categories and products.
 * 
 * It interacts with the "CategoryRepository" and "ProductRepository" to perform
 * CRUD operations on categories and products.
 * 
 * addCategory(CategoryRequest request): Adds a new category based on the
 * provided category request.
 */
@Service
@RequiredArgsConstructor
public class AdminService {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final DiscountCouponRepository discountCouponRepository;
    private final CartRepository cartRepository;
    private final ImageService imageService;
    private final UserRepository userRepository;
    private final MongoOperations mongoOperations;

    public void addCategory(CategoryRequest request) {
        if (categoryRepository.existsByCategoryName(request.getCategory())){
            throw new ConstraintException("Category Already Exists");
        }
        Category category = new Category();
        category.setCategoryName(request.getCategory());
        categoryRepository.save(category);
    }

    public void updateCategory(String name, CategoryRequest request) {
        Category category = categoryRepository.findByCategoryName(name)
                .orElseThrow(() -> new ItemNotFoundException("Category Not Found"));
        category.setCategoryName(request.getCategory());
        categoryRepository.save(category);
    }

    public void addProduct(ProductRequest productRequest) throws IOException {
        Product newProduct = new Product();
        for (String cat: productRequest.getCategory()){
            if (!categoryRepository.existsByCategoryName(cat)){
                throw new ItemNotFoundException("Category Not Found");
            }
            newProduct.getCategories().add(cat);
        }
        newProduct.setProductName(productRequest.getProductName());
        newProduct.setCostPrice(productRequest.getCostPrice());
        newProduct.setProfitPercentage(productRequest.getProfitPercentage()/100.0);
        double SP = productRequest.getCostPrice() +  productRequest.getCostPrice()*(productRequest.getProfitPercentage()/100.0);
        int price = (int) (Math.round(SP / 10.0) * 10.0);
        newProduct.setPrice(price-1);
        newProduct.setMRP(productRequest.getMRP());
        newProduct.setProductDescription(productRequest.getProductDescription());
        newProduct.setColors(productRequest.getColors());
        newProduct.setSizes(productRequest.getSizes());
        newProduct.setModel(productRequest.getModel());
        newProduct.setQuantity(productRequest.getQuantity());
        newProduct.setProductPics(productRequest.getProductPics().stream()
                .map(img->{
                    String id = UUID.randomUUID().toString();
                    try {
                        imageService.saveRawImage(id, img);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return id;
                }).toList());
        productRepository.save(newProduct);
    }

    public void updateProduct(String id, ProductRequest productRequest) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product Not Found"));
        for (String cat: productRequest.getCategory()){
            if (!categoryRepository.existsByCategoryName(cat)){
                throw new ItemNotFoundException("Category Not Found");
            }
            if (!product.getCategories().contains(cat))
                product.getCategories().add(cat);
        }
        product.setProductName(productRequest.getProductName());
        product.setCostPrice(productRequest.getCostPrice());
        product.setMRP(productRequest.getMRP());
        product.setProfitPercentage(productRequest.getProfitPercentage()/100.0);
        double SP = productRequest.getCostPrice() +  productRequest.getCostPrice()*(productRequest.getProfitPercentage()/100.0);
        int price = (int) (Math.round(SP / 10.0) * 10.0);
        product.setPrice(price-1);
        product.setProductDescription(productRequest.getProductDescription());
        product.setColors(productRequest.getColors());
        product.setSizes(productRequest.getSizes());
        product.setModel(productRequest.getModel());
        product.setQuantity(productRequest.getQuantity());
        product.setProductPics(productRequest.getProductPics().stream()
                .map(img->{
                    String dataId = UUID.randomUUID().toString();
                    try {
                        imageService.saveRawImage(dataId, img);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return dataId;
                }).toList());
        productRepository.save(product);
    }

    public void updateAll(ProductRequest updateAllRequest) {
        List<Product> products = productRepository.findAll();
        for (Product product: products){
            double SP = updateAllRequest.getCostPrice() +  updateAllRequest.getCostPrice()*(updateAllRequest.getProfitPercentage()/100.0);
            int price = (int) (Math.round(SP / 10.0) * 10.0);
            product.setPrice(price-1);
            productRepository.save(product);
        }
    }

    @Async
    protected void saveDiscount(String id, String coupon){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product Not Found"));
        product.getCoupons().add(coupon);
        productRepository.save(product);
    }

    public void createDiscounts(DiscountRequest discountRequest){
        DiscountCoupon discountCoupon = new DiscountCoupon();
        discountCoupon.setCouponCode(discountRequest.getCouponCode());
        discountCoupon.setDiscountPercentage(discountRequest.getDiscountPercentage()/100.0);
        discountCoupon.setValidity(discountRequest.getValidity());
        for(String id : discountRequest.getProducts()){
            saveDiscount(id, discountRequest.getCouponCode());
        }
        discountCouponRepository.save(discountCoupon);
    }
    @Async
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
    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void removeInActiveUsers(){
        for (User user: userRepository.findAllByVerified(false)){
            if (user.getOTPLimit().before(new Date())){
                cartRepository.delete(cartRepository.findCartByUserId(user.getId()).get());
                userRepository.delete(user);
            }
        }
    }
    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteCarts(){
        for (Cart cart: cartRepository.findAll()){
            Optional<User> user = userRepository.findById(cart.getUserId());
            if (user.isEmpty()){
                cartRepository.delete(cart);
            }
        }
    }

    public AdminProfitResponse getProfits(Date dateFrom, Date dateTo) {

        Query query = new Query();
        query.addCriteria(Criteria.where("orderDate").gte(dateFrom).lte(new Date(dateTo.getTime() + 1000*60*60*24)));
        List<Order> orders = mongoOperations.find(query, Order.class);
        Map<String, Double> profits = new HashMap<>();
        for (Order order : orders){

             Double profit = order.getOrderItems().stream()
                    .mapToDouble(orderItem ->{
                        Product product = productRepository.findById(orderItem.getProductId())
                                .orElseThrow(()-> new ItemNotFoundException("Product Not Found"));
                        return orderItem.getPrice() - product.getCostPrice();
                    })
                    .sum();
             profits.put(dateFormat.format(order.getOrderDate()),
                     profits.getOrDefault(dateFormat.format(order.getOrderDate()), 0.0) +profit);
        }
        return AdminProfitResponse.builder().profit(profits).build();
    }


    public List<DiscountResponse> viewDiscounts() {
        return discountCouponRepository.findAll().stream()
                .map(discountCoupon -> DiscountResponse.builder()
                        .discountPercentage((int) (discountCoupon.getDiscountPercentage()*100))
                        .couponCode(discountCoupon.getCouponCode())
                        .validity(discountCoupon.getValidity())
                        .products(productRepository.findAllByCouponsContains(discountCoupon.getCouponCode())
                                .stream()
                                .map(product -> ProductPreviewResponse.builder()
                                    .image(product.getProductPics().get(0))
                                    .productName(product.getProductName())
                                    .productId(product.getId())
                                    .build())
                                .toList())
                        .build())
                .toList();
    }
}
