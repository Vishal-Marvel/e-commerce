package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.*;
import com.KiyoInteriors.ECommerce.entity.Cart;
import com.KiyoInteriors.ECommerce.entity.Wishlist;
import com.KiyoInteriors.ECommerce.exceptions.APIException;
import com.KiyoInteriors.ECommerce.exceptions.ConstraintException;
import com.KiyoInteriors.ECommerce.exceptions.UserNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CartRepository;
import com.KiyoInteriors.ECommerce.repository.WishlistRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final WishlistRepository wishlistRepository;
    private final EmailService emailService;

    public void register(RegisterRequest userDTO) throws MessagingException {
        Optional<User> optionalUser = userRepository.findByEmail(userDTO.getEmail());
        if (optionalUser.isPresent()) {
            throw new ConstraintException("User Name or Email Already Exists");
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setRole(UserRole.ROLE_USER);
        user.setVerified(false);
        sendMail(user, "verify", "Registration");
        userRepository.save(user);
        Cart cart = new Cart(user.getId());
        cartRepository.save(cart);
        Wishlist wishlist = new Wishlist(user.getId());
        wishlistRepository.save(wishlist);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest loginDTO) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(), loginDTO.getPassword()));
        User user = userRepository.findByEmail(loginDTO.getEmail())
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

    public void verifyUser(String code) {
        User user = userRepository.findUserByOTP(code)
            .orElseThrow(()-> new UserNotFoundException("Invalid Code"));
        if(user.isVerified()){
            throw new APIException("User Already Activated", HttpStatus.ALREADY_REPORTED);
        }
        if(new Date().after(user.getOTPLimit())){
            throw new AccessDeniedException("Code Expired");
        }
        user.setVerified(true);
        user.setOTP(null);
        user.setOTPLimit(null);
        userRepository.save(user);

    }

    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
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
        User user = userRepository.findByEmail(resetPasswordRequest.getEmail())
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        sendMail(user, "resetpassword", "Reset Password");
    }

    public void requestVerify(ResetPasswordRequest resetPasswordRequest) throws MessagingException{
        User user = userRepository.findByEmail(resetPasswordRequest.getEmail())
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        sendMail(user, "verify", "Verification");
    }

    public void verifyUserAndChangeMail(String code) {
        User user = userRepository.findUserByOTP(code)
                .orElseThrow(()-> new UserNotFoundException("Invalid Code"));
        if(new Date().after(user.getOTPLimit())){
            throw new AccessDeniedException("Code Expired");
        }
        user.setOTP(null);
        user.setOTPLimit(null);
        user.setEmail(user.getTempEmail());
        user.setTempEmail(null);
        userRepository.save(user);

    }

    public void oAuth2register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(UserRole.ROLE_USER);
        user.setVerified(true);
        userRepository.save(user);
        Cart cart = new Cart(user.getId());
        cartRepository.save(cart);
        Wishlist wishlist = new Wishlist(user.getId());
        wishlistRepository.save(wishlist);
    }
}
