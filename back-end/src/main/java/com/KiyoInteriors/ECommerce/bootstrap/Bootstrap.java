package com.KiyoInteriors.ECommerce.bootstrap;

import com.KiyoInteriors.ECommerce.entity.UserRole;
import com.KiyoInteriors.ECommerce.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void run(final String... args) throws Exception {
        if (userRepository.findAll().size() == 0L) {
            User user = new User();
            user.setUsername("admin");
            user.setName("admin");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole(UserRole.ROLE_ADMIN);
            userRepository.save(user);
        }
    }

}
