package com.KiyoInteriors.ECommerce.DTO.Response;

import com.KiyoInteriors.ECommerce.entity.Image;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductPreviewResponse {
    private String productId;
    private String productName;
    private List<String> category;
    private Image image;
}
