package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.ReviewRequest;
import com.KiyoInteriors.ECommerce.entity.Order;
import com.KiyoInteriors.ECommerce.entity.Product;
import com.KiyoInteriors.ECommerce.entity.ReviewRating;
import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.repository.OrderRepository;
import com.KiyoInteriors.ECommerce.repository.ProductRepository;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service

public class ProductReviewService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public String giveReviewRating(User user, ReviewRequest request){
        List<Order> orders = orderRepository.findAllByUserId(user.getId());
        for(Order order: orders){
            if(order.getCartItems().stream().anyMatch(item->item.getProductId().equals(request.getProductId()))){
                Product product = productRepository.findById(request.getProductId())
                        .orElseThrow(()->new ItemNotFoundException("Product not found"));
                ReviewRating reviewRating = ReviewRating.builder()
                        .username(user.getUsername())
                        .review(request.getReview())
                        .rating(request.getRating())
                        .build();
                product.setRating((product.getRating()*product.getReviewRating().size() + request.getRating())/(product.getReviewRating().size()+1));
                Map<String, ReviewRating> reviews = product.getReviewRating();
                reviews.put(user.getId(), reviewRating);
                product.setReviewRating(reviews);
                productRepository.save(product);
                return "Thank You for your Review";
            }

        }
        return "You Cant Review";

    }
}
