package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.entity.Image;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {
    public Image compressImage(MultipartFile file) throws IOException {
        Thumbnails.Builder<? extends InputStream> builder = Thumbnails.of(file.getInputStream())
                .size(500, 500)
                .outputQuality(0.6);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        builder.toOutputStream(outputStream);
        outputStream.toByteArray();
        return Image.builder()
                .data(outputStream.toByteArray())
                .fileName(file.getOriginalFilename())
                .contentType(file.getContentType())
                .build();
    }
}
