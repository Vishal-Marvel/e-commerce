package com.KiyoInteriors.ECommerce.bootstrap;

import com.KiyoInteriors.ECommerce.entity.UserRole;
import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
/**
 * This class is used to bootstrap the application with an initial admin user.
 * It implements the CommandLineRunner interface to run some code after the application context is loaded.
 */
@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminService service;

    public void run(final String... args) throws Exception {
        if (userRepository.findAll().size() == 0L) {
            User user = new User();
            user.setUsername("admin");
            user.setEmail("admin@kiyo.com");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole(UserRole.ROLE_ADMIN);
            user.setVerified(true);
            userRepository.save(user);
        }
        service.deleteCarts();
        service.removeCoupons();
        service.removeInActiveUsers();

    }

}
