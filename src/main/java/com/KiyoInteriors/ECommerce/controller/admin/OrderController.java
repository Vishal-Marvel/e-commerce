package com.KiyoInteriors.ECommerce.controller.admin;

import com.KiyoInteriors.ECommerce.DTO.Response.AdminProfitResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.OrderResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.OrderResponses;
import com.KiyoInteriors.ECommerce.entity.Order;
import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.entity.UserRole;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.KiyoInteriors.ECommerce.exceptions.UserNotFoundException;
import com.KiyoInteriors.ECommerce.repository.OrderRepository;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import com.KiyoInteriors.ECommerce.service.AdminService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.KiyoInteriors.ECommerce.DTO.Request.SmsRequest;
import com.KiyoInteriors.ECommerce.DTO.Request.UpdateOrderRequest;
import com.KiyoInteriors.ECommerce.DTO.Response.MiscResponse;
import com.KiyoInteriors.ECommerce.service.OrderService;
import com.KiyoInteriors.ECommerce.service.SmsSenderService;

import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/order")
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")

public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final AdminService service;
    private final SmsSenderService smsSenderService;

    @PutMapping("/update-status")
    public ResponseEntity<MiscResponse> updateOrderStatus(@RequestBody UpdateOrderRequest updateOrderRequest) {
        orderService.orderStatusUpdateAdmin(updateOrderRequest);
        return ResponseEntity.ok(MiscResponse.builder().response("OrderStatusUpdated").build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> orderDetails(@PathVariable String id) {

        return ResponseEntity.ok(orderService.orderDetails(id));

    }

    @GetMapping
    public ResponseEntity<List<OrderResponses>> getAllOrders(
            @RequestParam(value = "date", required = false) Date date,
            @RequestParam(value = "userId", required = false) String id) {
        return ResponseEntity.ok(orderService.displayAllOrder(date, id));
    }

    @GetMapping("/profits")
    public ResponseEntity<AdminProfitResponse> profits(
            @RequestParam(value = "dateFrom") Date dateFrom,
            @RequestParam(value = "dateTo") Date dateTo) {
        return ResponseEntity.ok(service.getProfits(dateFrom, dateTo));
    }

    @PostMapping("/sendsms")
    public ResponseEntity<MiscResponse> messageController(@RequestBody SmsRequest smsRequest) {
        smsSenderService.sendSms(smsRequest);
        return ResponseEntity.ok(MiscResponse.builder().response("SmsSent").build());
    }

}
