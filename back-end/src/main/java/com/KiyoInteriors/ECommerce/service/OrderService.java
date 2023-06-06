package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.OrderRequest;
import com.KiyoInteriors.ECommerce.DTO.Request.UpdateOrderRequest;
import com.KiyoInteriors.ECommerce.entity.Cart;
import com.KiyoInteriors.ECommerce.entity.Order;
import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CartRepository;
import com.KiyoInteriors.ECommerce.repository.OrderRepository;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * The OrderService class provides functionality for creating orders in the
 * online store.
 * It interacts with the CartRepository and OrderRepository to retrieve cart
 * information
 * and save the created order, respectively.
 * The OrderService class has the following fields:
 * cartRepository: an instance of CartRepository used to fetch cart information.
 * orderRepository: an instance of OrderRepository used to save the created
 * order.
 * The createOrder method accepts a User object and an OrderRequest object as
 * parameters.
 * It first retrieves the user's cart using the cartRepository. If the cart is
 * not found,
 * it throws an ItemNotFoundException indicating that the cart could not be
 * found.
 * Then, it creates a new Order object and sets its properties such as order
 * date, coupon,
 * billing address, and shipping address based on the values provided in the
 * OrderRequest.
 * The cart items are obtained by mapping the item IDs from the request to the
 * actual cart items
 * using the cart.getCartItem() method. The retrieved cart items are then set on
 * the order.
 * Finally, the order is saved using the orderRepository.
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public void createOrder(User user, OrderRequest request) {
        Cart cart = cartRepository.findCartByUserId(user.getId())
                .orElseThrow(() -> new ItemNotFoundException("Cart Not Found"));
        Order order = new Order(user.getId());
        order.setOrderDate(new Date());
        order.setCoupon(request.getCoupon());
        order.setBillingAddress(request.getBillingAddress());
        order.setShippingAddress(request.getShippingAddress());
        order.setCartItems(request.getItems()
                .stream()
                .map(id -> cart.getCartItem().get(id))
                .toList());
        orderRepository.save(order);
    }

    public void orderStatusUpdateAdmin(UpdateOrderRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new ItemNotFoundException("Order Not Found"));
        order.setOrderStatus(request.getUpdatedStatus());
        orderRepository.save(order);
    }
}
