package com.KiyoInteriors.ECommerce.DTO.Response;

import com.KiyoInteriors.ECommerce.entity.Image;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class CartProductsResponse {
    private String id;
    private String name;
    private String size;
    private Double prize;
    private Integer quantity;
    private Image image;

}
