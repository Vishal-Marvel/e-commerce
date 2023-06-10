package com.KiyoInteriors.ECommerce.DTO.Response;

import com.KiyoInteriors.ECommerce.entity.Image;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishlistResponse {
    private String productId;
    private String productName;
    private Image image;
}
