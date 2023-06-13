package com.KiyoInteriors.ECommerce.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
/**
 * A class representing an Admin Wishlist response.
 * This class encapsulates the wishlist information for different products in a map format.
 */
@Data
@Builder
public class AdminWishListResponse {
    private Map<ProductPreviewResponse, Integer> products;

}
