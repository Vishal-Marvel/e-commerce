package com.KiyoInteriors.ECommerce.service;

import java.io.IOException;

import com.KiyoInteriors.ECommerce.DTO.Response.UserResponse;
import com.KiyoInteriors.ECommerce.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.DTO.Request.UserRequest;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;


@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;

    public void updateUser(final UserRequest userDTO, final String id) throws IOException {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setMobile(userDTO.getMobile());
        user.setAddresses(userDTO.getAddresses());
        Image image = Image.builder()
                .data(userDTO.getPhoto().getBytes())
                .fileName(userDTO.getPhoto().getOriginalFilename())
                .contentType(userDTO.getPhoto().getContentType())
                .build();
        user.setPhoto(image);
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
                .name(user.getName())
                .build();
    }
}
