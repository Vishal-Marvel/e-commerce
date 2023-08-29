package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.entity.RawImage;
import com.KiyoInteriors.ECommerce.repository.RawImageRepository;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final RawImageRepository rawImageRepository;
    public byte[] compressImage(MultipartFile file) throws IOException {
        if (file == null) {
            file = new FileToMultipartFileConverter()
                    .convert(new File("src\\main\\resources\\static\\nouserimage.jpg"));
        }
        if (Objects.requireNonNull(file.getContentType()).contains("png")){
            throw new IOException("png File Type Not Supported");
        }
        Thumbnails.Builder<? extends InputStream> builder = Thumbnails.of(file.getInputStream())
                .size(650, 650)
                .outputQuality(0.9);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        builder.toOutputStream(outputStream);
        return outputStream.toByteArray();
    }

    @Async
    protected void saveRawImage(String id, MultipartFile file) throws IOException {
        RawImage rawImage = new RawImage();
        rawImage.setId(id);
        rawImage.setData(compressImage(file));
        rawImageRepository.save(rawImage);

    }
}
