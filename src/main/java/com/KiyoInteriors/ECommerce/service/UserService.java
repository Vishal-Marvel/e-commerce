package com.KiyoInteriors.ECommerce.service;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import com.KiyoInteriors.ECommerce.DTO.Request.ChangeMailRequest;
import com.KiyoInteriors.ECommerce.DTO.Request.SetProfileRequest;
import com.KiyoInteriors.ECommerce.DTO.Response.UserResponse;
import com.KiyoInteriors.ECommerce.entity.RawImage;
import com.KiyoInteriors.ECommerce.repository.RawImageRepository;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**

The "UserService" class provides methods for updating user details and converting a User object to a UserResponse object.
updateUser(UserRequest userDTO, String id): Updates the user details based on the provided UserRequest object and user ID.
*/

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final EmailService emailService;
    private final RawImageRepository rawImageRepository;

    public void updateUser(final @Valid SetProfileRequest userDTO, final String id) throws IOException {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        user.setMobile(userDTO.getMobile());
        user.setAddresses(userDTO.getAddresses());
        if (userDTO.getPhoto()!=null) {
            String dataId = UUID.randomUUID().toString();
            user.setPhoto(dataId);
            imageService.saveRawImage(dataId, userDTO.getPhoto());
        }
        userRepository.save(user);
    }

    public UserResponse convertUserToResponse(User user) {
        return UserResponse.builder()
                .photo(user.getPhoto())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .addresses(user.getAddresses())
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    public void changeMail(User user, ChangeMailRequest changeMailRequest) throws MessagingException {
        String otp = UUID.randomUUID().toString();
        user.setOTP(otp);
        user.setOTPLimit(new Date(new Date().getTime()+1000*60*60));
        user.setTempEmail(changeMailRequest.getNewMail());
        emailService.sendVerificationEmail(changeMailRequest.getNewMail(), otp,"New Mail Verification","email-verify" );
        userRepository.save(user);
    }
}
