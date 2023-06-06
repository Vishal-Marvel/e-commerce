package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;

@Data
public class UpdateOrderRequest {
    private String orderId;
    private String updatedStatus;
}