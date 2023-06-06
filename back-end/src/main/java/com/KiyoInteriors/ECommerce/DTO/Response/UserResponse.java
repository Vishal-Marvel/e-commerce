package com.KiyoInteriors.ECommerce.DTO.Response;

import com.KiyoInteriors.ECommerce.entity.Image;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * This class represents a user response object that contains the user's
 * information.
 * It has the following fields:
 * - id: a unique identifier for the user
 * - username: a string that the user uses to log in
 * - name: a string that contains the user's full name
 * - mobile: a string that contains the user's mobile number
 * - addresses: a list of strings that contains the user's addresses
 * - photo: an image object that contains the user's photo
 * - email: a string that contains the user's email address
 */
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
