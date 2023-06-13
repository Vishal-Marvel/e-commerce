package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.OrderRequest;
import com.KiyoInteriors.ECommerce.DTO.Request.UpdateOrderRequest;
import com.KiyoInteriors.ECommerce.DTO.Response.CartProductsResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.OrderResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.OrderResponses;
import com.KiyoInteriors.ECommerce.entity.*;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CartRepository;
import com.KiyoInteriors.ECommerce.repository.DiscountCouponRepository;
import com.KiyoInteriors.ECommerce.repository.OrderRepository;
import com.KiyoInteriors.ECommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
        The OrderService class provides functionality for creating orders in the online store.
        It interacts with the CartRepository and OrderRepository to retrieve cart information
        and save the created order, respectively.
        The OrderService class has the following fields:
        cartRepository: an instance of CartRepository used to fetch cart information.
        orderRepository: an instance of OrderRepository used to save the created order.
        The createOrder method accepts a User object and an OrderRequest object as parameters.
        It first retrieves the user's cart using the cartRepository. If the cart is not found,
        it throws an ItemNotFoundException indicating that the cart could not be found.
        Then, it creates a new Order object and sets its properties such as order date, coupon,
        billing address, and shipping address based on the values provided in the OrderRequest.
        The cart items are obtained by mapping the item IDs from the request to the actual cart items
        using the cart.getCartItem() method. The retrieved cart items are then set on the order.
        Finally, the order is saved using the orderRepository.
*/
@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MongoOperations mongoOperations;
    private final DiscountCouponRepository discountCouponRepository;

    public void createOrder(User user, OrderRequest request) {
        Cart cart = cartRepository.findCartByUserId(user.getId())
                .orElseThrow(()->new ItemNotFoundException("Cart Not Found"));
        Order order = new Order(user.getId());
        order.setOrderDate(new Date());
        order.setBillingAddress(request.getBillingAddress());
        order.setShippingAddress(request.getShippingAddress());
        order.setOrderItems(request.getItems()
                .stream()
                .map(id->{
                    CartItem cartItem = cart.getCartItem().get(id);
                    Product product = productRepository.findById(cartItem.getProductId())
                            .orElseThrow(() -> new ItemNotFoundException("Product Not Found"));
                    if (cartItem.getQuantity() > product.getQuantity()) {
                        throw new ItemNotFoundException("Quantity insufficient");
                    }
                    OrderItem orderItem =  OrderItem.builder()
                            .id(cartItem.getId())
                            .productId(cartItem.getProductId())
                            .quantity(cartItem.getQuantity())
                            .size(cartItem.getSize())
                            .color(cartItem.getColor())
                            .build();

                    if (product.getCoupons().contains(request.getCoupon())){
                        DiscountCoupon coupon = discountCouponRepository.findByCouponCode(request.getCoupon())
                                .orElseThrow(() ->new ItemNotFoundException("Coupon Not Exits"));
                        double SP = product.getPrice() - product.getPrice()*coupon.getDiscountPercentage();
                        int price = (int) (Math.round(SP / 10.0) * 10.0);
                        orderItem.setPrice(price-1);
                    }
                    else{
                        orderItem.setPrice(product.getPrice());
                    }
                    return orderItem;

                })
                .toList());
        order.setOrderStatus("PENDING");
        order.setTotal(request.getItems().stream()
                        .mapToDouble(id->{
                            CartItem cartItem = cart.getCartItem().get(id);
                            Product product = productRepository.findById(cartItem.getProductId())
                                    .orElseThrow(() -> new ItemNotFoundException("Product Not Found"));
                            if (product.getCoupons().contains(request.getCoupon())){
                                DiscountCoupon coupon = discountCouponRepository.findByCouponCode(request.getCoupon())
                                        .orElseThrow(() ->new ItemNotFoundException("Coupon Not Exits"));
                                 return (product.getPrice() - product.getPrice()*coupon.getDiscountPercentage());
                            }
                            return product.getPrice();
                        })
                        .sum());
        orderRepository.save(order);
        updateProductQuantity(order);
        removeCartItems(order);
    }

    @Async
    protected void removeCartItems(Order order) {
        if (Objects.equals(order.getOrderStatus(), "PLACED")){
            Cart cart = cartRepository.findCartByUserId(order.getUserId())
                    .orElseThrow(() -> new ItemNotFoundException("Cart Not Found"));
            for (OrderItem orderItem : order.getOrderItems()){
                cart.getCartItem().remove(orderItem.getId());
            }
            cartRepository.save(cart);

        }
    }
    @Async
    protected void updateProductQuantity(Order order) {
        if (Objects.equals(order.getOrderStatus(), "PLACED")) {
            for (OrderItem item : order.getOrderItems()) {
                Product product = productRepository.findById(item.getProductId())
                        .orElseThrow(() -> new ItemNotFoundException("Product Not Found"));
                product.setQuantity(product.getQuantity() - item.getQuantity());
                productRepository.save(product);
            }
        }
    }

    public void orderStatusUpdateAdmin(UpdateOrderRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new ItemNotFoundException("Order Not Found"));
        order.setOrderStatus(request.getUpdatedStatus());
        orderRepository.save(order);
    }

    public List<OrderResponses> displayAllOrder(Date date, String id) {
        Query query = new Query();
        if (date!= null){
            query.addCriteria(Criteria.where("orderDate").gte(date).lte(new Date(date.getTime() + 1000*60*60*24)));
        }
        if (id!=null){
            query.addCriteria(Criteria.where("userId").is(id));
        }
        List<Order> orders = mongoOperations.find(query, Order.class);
        return orders.stream()
                .map(order -> {
                    OrderResponses orderResponse = new OrderResponses();
                    orderResponse.setOrderId(order.getId());
                    orderResponse.setOrderDate(order.getOrderDate());
                    orderResponse.setOrderStatus(order.getOrderStatus());
                    orderResponse.setAmount(order.getTotal());
                    orderResponse.setItems(order.getOrderItems()
                            .stream()
                            .map(orderItem -> {
                                Product product = productRepository.findById(orderItem.getProductId())
                                        .orElseThrow(() -> new ItemNotFoundException("Product Not Found"));
                                return CartProductsResponse.builder()
                                        .quantity(orderItem.getQuantity())
                                        .model(product.getModel())
                                        .size(orderItem.getSize())
                                        .color(orderItem.getColor())
                                        .price(orderItem.getPrice())
                                        .name(product.getProductName())
                                        .image(product.getProductPics().get(0))
                                        .itemId(orderItem.getId())
                                        .build();
                            })
                            .toList());
                    return orderResponse;
                })
                .toList();
    }

    public OrderResponse orderDetails(String orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ItemNotFoundException("Item for order ID not found"));
        OrderResponse orderResponse = new OrderResponse(orderId,
                order.getTotal(),
                order.getOrderDate(),
                order.getOrderStatus(),
                order.getBillingAddress(),
                order.getShippingAddress(),
                null,
                order.getOrderItems()
                        .stream()
                        .map(orderItem -> {
                            Product product = productRepository.findById(orderItem.getProductId())
                                    .orElseThrow(() -> new ItemNotFoundException("Product Not Found"));
                            return CartProductsResponse.builder()
                                    .quantity(orderItem.getQuantity())
                                    .model(product.getModel())
                                    .size(orderItem.getSize())
                                    .color(orderItem.getColor())
                                    .price(orderItem.getPrice())
                                    .name(product.getProductName())
                                    .image(product.getProductPics().get(0))
                                    .itemId(orderItem.getId())
                                    .build();
                        })
                        .toList());
        return orderResponse;
    }

}
