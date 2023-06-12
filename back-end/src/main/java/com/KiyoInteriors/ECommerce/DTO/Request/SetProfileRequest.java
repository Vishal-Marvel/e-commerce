package com.KiyoInteriors.ECommerce.DTO.Request;

import com.KiyoInteriors.ECommerce.entity.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class SetProfileRequest {
    @NotBlank(message = "Name is necessary")
    private String name;
    @NotBlank(message = "Mobile Number is necessary")
    private String mobile;
    private List<Address> addresses;
    private MultipartFile photo;
}