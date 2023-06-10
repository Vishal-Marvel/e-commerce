package com.KiyoInteriors.ECommerce.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class AdminProfitResponse {
    private Map<String, Double> profit;
}
