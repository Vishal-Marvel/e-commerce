package com.KiyoInteriors.ECommerce.entity;

import lombok.Builder;
import lombok.Data;
/**
 * This class represents an image file that can be stored and retrieved from a database.
 * It has three fields: fileName, data and contentType.
 * The fileName is the name of the image file, such as "picture.jpg".
 * The data is an array of bytes that contains the binary content of the image file.
 * The contentType is a string that indicates the MIME type of the image file, such as "image/jpeg".
 */
@Data
@Builder
public class Image {
    private String fileName;
    private byte[] data;
    private String filePath;
    private String contentType;
}
