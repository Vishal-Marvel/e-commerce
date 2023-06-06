package com.KiyoInteriors.ECommerce.controller.admin;

import com.KiyoInteriors.ECommerce.DTO.Response.OrderResponses;
import org.springframework.http.ResponseEntity;
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

    @PutMapping
    public ResponseEntity<MiscResponse> updateOrderStatus(@RequestBody UpdateOrderRequest updateOrderRequest) {
        orderService.orderStatusUpdateAdmin(updateOrderRequest);
        return ResponseEntity.ok(MiscResponse.builder().response("OrderStatusUpdated").build());

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
