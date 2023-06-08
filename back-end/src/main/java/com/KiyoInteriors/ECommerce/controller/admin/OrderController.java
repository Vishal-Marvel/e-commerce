package com.KiyoInteriors.ECommerce.controller.admin;

import com.KiyoInteriors.ECommerce.DTO.Response.OrderResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.OrderResponses;
import com.KiyoInteriors.ECommerce.entity.Order;
import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.entity.UserRole;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.exceptions.UserNotFoundException;
import com.KiyoInteriors.ECommerce.repository.OrderRepository;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.KiyoInteriors.ECommerce.DTO.Request.UpdateOrderRequest;
import com.KiyoInteriors.ECommerce.DTO.Response.MiscResponse;
import com.KiyoInteriors.ECommerce.service.OrderService;

import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/order")

public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PutMapping
    public ResponseEntity<MiscResponse> updateOrderStatus(@RequestBody UpdateOrderRequest updateOrderRequest) {
        orderService.orderStatusUpdateAdmin(updateOrderRequest);
        return ResponseEntity.ok(MiscResponse.builder().response("OrderStatusUpdated").build());

    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> orderDetails(@PathVariable String id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Order Not Found"));
        return ResponseEntity.ok(orderService.orderDetails(id));

    }

    @GetMapping
    public ResponseEntity<List<OrderResponses>> getAllOrders(
            @RequestParam(value = "data", required = false) Date date
    ){
        if (date!=null)
            return ResponseEntity.ok(orderService.displayAllOrder(date));
        else
            return ResponseEntity.ok(orderService.displayAllOrder());
    }

}
