package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.*;
import com.KiyoInteriors.ECommerce.entity.Cart;
import com.KiyoInteriors.ECommerce.exceptions.ConstraintException;
import com.KiyoInteriors.ECommerce.exceptions.UserNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CartRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.KiyoInteriors.ECommerce.DTO.Response.AuthenticationResponse;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.KiyoInteriors.ECommerce.entity.UserRole;
import com.KiyoInteriors.ECommerce.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.KiyoInteriors.ECommerce.security.JWTTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * 
 * The "AuthenticationService" class is responsible for user authentication,
 * registration, password change, and logout operations.
 * * It interacts with the "UserRepository", "AuthenticationManager",
 * "JWTTokenProvider", "PasswordEncoder", and "CartRepository" to perform these
 * operations.
 * register(UserRequest userDTO): Registers a new user based on the provided
 * user request.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final ImageService imageService;
    private final EmailService emailService;

    public void register(final UserRequest userDTO) throws IOException, MessagingException {
        Optional<User> optionalUser = userRepository.findUserByUsernameOrEmail(userDTO.getUsername(),
                userDTO.getEmail());
        if (optionalUser.isPresent()) {
            throw new ConstraintException("User Name or Email Already Exists");
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setMobile(userDTO.getMobile());
        user.setAddresses(userDTO.getAddresses());
        user.setPhoto(imageService.compressImage(userDTO.getPhoto()));
        user.setRole(UserRole.ROLE_USER);
        user.setVerified(false);
        sendMail(user, "verify", "Registration");
        userRepository.save(user);
        Cart cart = new Cart(user.getId());
        cartRepository.save(cart);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest loginDTO) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
        User user = userRepository.findUserByUsernameOrEmail(
                loginDTO.getUsernameOrEmail(), loginDTO.getUsernameOrEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User Not found"));
        if (!user.isVerified()){
            throw new AccessDeniedException("User not Verified");
        }
        SecurityContextHolder.getContext().setAuthentication(auth);
        user.setLastLoggedIn(new Date());
        userRepository.save(user);
        return AuthenticationResponse.builder()
                .token(jwtTokenProvider
                        .generateToken(auth))
                .role(user.getRole().name())
                .build();
    }

    public void verifyUser(String code) throws Exception {
        User user = userRepository.findUserByOTP(code)
            .orElseThrow(()-> new UserNotFoundException("Invalid Code"));
        if(new Date().after(user.getOTPLimit())){
            throw new AccessDeniedException("Code Expired");
        }
        if(user.isVerified()){
            throw new Exception("User Already Activated");
        }
        user.setVerified(true);
        user.setOTP(null);
        user.setOTPLimit(null);
        userRepository.save(user);

    }

    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    public void resetPassword(String code, ResetPasswordDTO resetPasswordRequest) {
        User user = userRepository.findUserByOTP(code)
                .orElseThrow(()->new UserNotFoundException("Invalid Code"));
        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
        user.setOTP(null);
        user.setOTPLimit(null);
        userRepository.save(user);

    }
    public void sendMail(User user, String endpoint, String purpose) throws MessagingException {
        user.setOTPLimit(new Date(new Date().getTime()+1000*60*60));
        String otp = UUID.randomUUID().toString();
        user.setOTP(otp);

        emailService.sendVerificationEmail(user.getEmail(), otp, purpose, endpoint);
        userRepository.save(user);

    }

    public void requestResetPassword(ResetPasswordRequest resetPasswordRequest) throws MessagingException {
        User user = userRepository.findUserByUsernameOrEmail(resetPasswordRequest.getUsernameOrEmail() , resetPasswordRequest.getUsernameOrEmail())
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        sendMail(user, "reset-password", "Reset Password");
    }

    public void requestVerify(ResetPasswordRequest resetPasswordRequest) throws MessagingException{
        User user = userRepository.findUserByUsernameOrEmail(resetPasswordRequest.getUsernameOrEmail() , resetPasswordRequest.getUsernameOrEmail())
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        sendMail(user, "verify", "Verification");
    }
}
