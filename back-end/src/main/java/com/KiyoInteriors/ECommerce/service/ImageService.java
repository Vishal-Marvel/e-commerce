package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.entity.Image;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageService {
    public Image compressImage(MultipartFile file) throws IOException {
        if (file == null) {
            file = new FileToMultipartFileConverter()
                    .convert(new File("src\\main\\resources\\static\\nouserimage.jpg"));
        }
        if (Objects.requireNonNull(file.getContentType()).contains("png")){
            throw new IOException("png File Type Not Supported");
        }
        Thumbnails.Builder<? extends InputStream> builder = Thumbnails.of(file.getInputStream())
                .size(500, 500)
                .outputQuality(0.7);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        builder.toOutputStream(outputStream);
        outputStream.toByteArray();
        return Image.builder()
                .data(outputStream.toByteArray())
                .fileName(file.getOriginalFilename())
                .contentType(file.getContentType())
                .build();
    }
    public Image saveImage(MultipartFile file) throws IOException {
        String uniqueFilename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        String imagePath = "src\\main\\resources\\static\\uploads/" + uniqueFilename;

        // Save the image file to the uploads directory
        Files.copy(file.getInputStream(), Paths.get(imagePath), StandardCopyOption.REPLACE_EXISTING);

        // Set the image path in the product object
        return Image.builder()
                .contentType(file.getContentType())
                .fileName(uniqueFilename)
                .filePath(imagePath).build();
//        product.setImagePath(imagePath);
//
//        // Save the product in MongoDB
//        return productRepository.save(product);
    }
}
