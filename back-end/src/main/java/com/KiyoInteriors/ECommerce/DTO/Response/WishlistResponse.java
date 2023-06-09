package com.KiyoInteriors.ECommerce.DTO.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishlistResponse {
    private String id;
    private String productId;
}
