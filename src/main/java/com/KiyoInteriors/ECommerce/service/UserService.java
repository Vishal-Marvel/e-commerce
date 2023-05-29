package com.KiyoInteriors.ECommerce.service;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.DTO.UserDTO;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;

    public void updateUser(final UserDTO userDTO, final String id) throws IOException {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setMobile(userDTO.getMobile());
        user.setAddresses(userDTO.getAddresses());
        user.setPhoto(userDTO.getPhoto().getBytes());
        userRepository.save(user);
    }

}
