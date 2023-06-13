package com.KiyoInteriors.ECommerce.DTO.Response;

import lombok.Builder;
import lombok.Data;
/**
 * A class representing an Image Response.
 * This class encapsulates the image data in the form of a byte array.
 */
@Data
@Builder
public class ImageResponse {
    private byte[] image;
}
