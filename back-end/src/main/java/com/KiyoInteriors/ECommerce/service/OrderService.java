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
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
                            .id(UUID.randomUUID().toString())
                            .productId(cartItem.getProductId())
                            .quantity(cartItem.getQuantity())
                            .size(cartItem.getSize())
                            .color(cartItem.getColor())
                            .build();

                    if (product.getCoupons().contains(request.getCoupon())){
                        DiscountCoupon coupon = discountCouponRepository.findByCouponCode(request.getCoupon())
                                .orElseThrow(() ->new ItemNotFoundException("Coupon Not Exits"));
                        orderItem.setPrice(product.getPrice() - product.getPrice()*coupon.getDiscountPercentage());
                    }
                    else{
                        orderItem.setPrice(product.getPrice());
                    }
                    return orderItem;

                })
                .toList());
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
    }

    public void orderStatusUpdateAdmin(UpdateOrderRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new ItemNotFoundException("Order Not Found"));
        order.setOrderStatus(request.getUpdatedStatus());
        orderRepository.save(order);
    }

    public List<OrderResponses> displayAllOrder() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> {
                    OrderResponses orderResponse = new OrderResponses();
                    orderResponse.setOrderId(order.getId());
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
    public List<OrderResponses> displayAllOrder(String id) {
        List<Order> orders = orderRepository.findAllByUserId(id);
        return orders.stream()
                .map(order -> {
                    OrderResponses orderResponse = new OrderResponses();
                    orderResponse.setOrderId(order.getId());
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

    public List<OrderResponses> displayAllOrder(Date date) {
        List<Order> orders = orderRepository.findAllByOrderDateAfter(date);
        return orders.stream()
                .map(order -> {
                    OrderResponses orderResponse = new OrderResponses();
                    orderResponse.setOrderId(order.getId());
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
