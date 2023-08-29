package com.KiyoInteriors.ECommerce.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
/**
 * A class representing an Admin Profit response.
 * This class encapsulates the profit information for different categories in a map format.
 */
@Data
@Builder
public class AdminProfitResponse {
    private Map<String, Double> profit;
}
