package com.KiyoInteriors.ECommerce.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KiyoInteriors.ECommerce.DTO.Request.UpdateOrderRequest;
import com.KiyoInteriors.ECommerce.DTO.Response.MiscResponse;
import com.KiyoInteriors.ECommerce.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/order")

public class OrderController {
    private final OrderService orderService;

    @PutMapping
    public ResponseEntity<MiscResponse> updateOrderStatus(@RequestBody UpdateOrderRequest updateOrderRequest) {
        orderService.orderStatusUpdateAdmin(updateOrderRequest);
        return ResponseEntity.ok(MiscResponse.builder().response("OrderStatusUpdated").build());

    }

}
