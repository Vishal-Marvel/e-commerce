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

/**

* This class represents a service for adding review ratings to products.
* It provides functionality to allow users to give reviews and ratings for products.
* The service interacts with the ProductRepository and OrderRepository to fetch
* relevant data and update the product's review ratings.
The ProductReviewService class has the following fields:
    * productRepository: an instance of ProductRepository used to fetch product information.
    * orderRepository: an instance of OrderRepository used to fetch order information.
    * The giveReviewRating method accepts a User object and a ReviewRequest object as parameters
        and returns a string indicating the result of the review submission. It first retrieves the
        user's orders and checks if the requested product is present in any of the orders. If so,
        it retrieves the product from the productRepository and creates a ReviewRating object using
        the user's information and the review request. The product's rating is then updated by taking
        into account the existing ratings and the new rating provided. The review is added to the
        product's reviewRating map. Finally, the updated product is saved using the productRepository.
    * If the requested product is not found in any of the user's orders, the method returns
    "You Cant Review" indicating that the user is not eligible to review the product.
    If the review submission is successful, the method returns "Thank You for your Review".
*/
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
