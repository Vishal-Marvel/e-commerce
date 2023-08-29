package com.KiyoInteriors.ECommerce.DTO.Response;

import lombok.Builder;
import lombok.Data;
/**
 * A class representing a Product Preview Response.
 * This class encapsulates the information related to a product preview, including its ID, name, and image.
 */
@Data
@Builder
public class ProductPreviewResponse {
    private String productId;
    private String productName;
    private String image;
}
