package com.KiyoInteriors.ECommerce.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Image {
    private String fileName;
    private byte[] data;
    private String contentType;
}
