package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.OrderRequest;
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

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    public void createOrder(User user, OrderRequest request) {
        Cart cart = cartRepository.findCartByUserId(user.getId())
                .orElseThrow(()->new ItemNotFoundException("Cart Not Found"));
        Order order = new Order(user.getId());
        order.setOrderDate(new Date());
        order.setCoupon(request.getCoupon());
        order.setBillingAddress(request.getBillingAddress());
        order.setShippingAddress(request.getShippingAddress());
        order.setCartItems(request.getItems()
                .stream()
                .map(id->cart.getCartItem().get(id))
                .toList());
        orderRepository.save(order);
    }
}
