package com.KiyoInteriors.ECommerce.DTO.Response;

import com.KiyoInteriors.ECommerce.entity.Image;
import lombok.Data;

@Data
public class ProductPreview {
    private String id;
    private String name;
    private Image image;
}
