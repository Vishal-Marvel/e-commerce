package com.KiyoInteriors.ECommerce.DTO.Response;

import com.KiyoInteriors.ECommerce.entity.Image;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class UserResponse {
    private String id;
    private String username;
    private String name;
    private String mobile;
    private List<String> addresses;
    private Image photo;
    private String email;

}
