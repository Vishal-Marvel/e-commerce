package com.KiyoInteriors.ECommerce.DTO.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageResponse {
    private byte[] image;
}
