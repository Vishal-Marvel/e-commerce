package com.KiyoInteriors.ECommerce.DTO.Request;

import com.KiyoInteriors.ECommerce.entity.Address;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/**
 * A class representing a Set Profile request.
 * This class encapsulates the information required to set a user's profile.
 */
@Data
public class SetProfileRequest {
    private String mobile;
    private List<Address> addresses;
    private MultipartFile photo;
}
