package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.*;
import com.KiyoInteriors.ECommerce.DTO.Response.AuthenticationResponse;
import com.KiyoInteriors.ECommerce.entity.Cart;
import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.entity.UserRole;
import com.KiyoInteriors.ECommerce.entity.Wishlist;
import com.KiyoInteriors.ECommerce.exceptions.APIException;
import com.KiyoInteriors.ECommerce.exceptions.ConstraintException;
import com.KiyoInteriors.ECommerce.exceptions.UserNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CartRepository;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import com.KiyoInteriors.ECommerce.repository.WishlistRepository;
import com.KiyoInteriors.ECommerce.security.JWTTokenProvider;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class AuthenticationServiceTest {
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;
    @Mock
    private CartRepository cartRepository;
    @Captor
    private ArgumentCaptor<User> userCaptor;
    @Mock
    private WishlistRepository wishlistRepository;
    @Mock
    private JWTTokenProvider jwtTokenProvider;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private EmailService emailService;
    @Mock
    private ImageService imageService;
    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
//        System.out.println("passwordEncoder = " + passwordEncoder.encode("hello"));
    }

    @Test
    void testAuthenticate_ValidCredentials_ReturnsResponseWithToken() {
        AuthenticationRequest loginDTO = new AuthenticationRequest("test@example.com", "password123");
        Authentication auth = mock(Authentication.class);
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setRole(UserRole.ROLE_USER);
        user.setVerified(true);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(user));
        when(jwtTokenProvider.generateToken(auth)).thenReturn("jwtToken");

        // Act
        AuthenticationResponse response = authenticationService.authenticate(loginDTO);

        // Assert
        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());
        assertEquals(UserRole.ROLE_USER.name(), response.getRole());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository, times(1)).findByEmail(loginDTO.getEmail());
        verify(jwtTokenProvider, times(1)).generateToken(auth);
        assertEquals(auth, SecurityContextHolder.getContext().getAuthentication());
        assertNotNull(user.getLastLoggedIn());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testAuthenticate_responseUserNotVerified() {
        AuthenticationRequest loginDTO = new AuthenticationRequest("test@example.com", "password123");
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setRole(UserRole.ROLE_USER);
        user.setVerified(false);
        // Act
        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(user));

        // Assert
        assertThrows(AccessDeniedException.class, () -> authenticationService.authenticate(loginDTO));

    }

    @Test
    public void testRegister() throws MessagingException {
        // Mock the userRepository to return an empty optional, indicating user does not exist
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        RegisterRequest userDTO = new RegisterRequest();
        // Set up the userDTO object with necessary values
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("Password");
        userDTO.setUsername("username");

        authenticationService.register(userDTO);
        
        // Verify that userRepository.save(user) is called twice and capture it
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(2)).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        System.out.println("capturedUser = " + capturedUser);

        // Verify that cartRepository.save(cart) is called once
        verify(cartRepository, times(1)).save(any(Cart.class));

        // Verify that wishlistRepository.save(wishlist) is called once
        verify(wishlistRepository, times(1)).save(any(Wishlist.class));
        // Assert Check the username, password, email
        assertEquals(userDTO.getEmail(), capturedUser.getEmail());
        assertEquals(userDTO.getUsername(), capturedUser.getUsername());
//        assertTrue(passwordEncoder.matches(userDTO.getPassword(), capturedUser.getPassword()));
    }

    @Test
    public void testRegisterUserExists()  {
        // Mock the userRepository to return a non-empty optional, indicating user already exists
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));

        RegisterRequest userDTO = new RegisterRequest();
        userDTO.setEmail("test@example.com");
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setRole(UserRole.ROLE_USER);
        user.setVerified(false);
        // Act
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(user));
        // Set up the userDTO object with necessary values
        assertThrows(ConstraintException.class, () ->
                authenticationService.register(userDTO));

        // Verify that userRepository.save(user) is not called
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void verifyUser_ValidCode(){
        // Create a mock User object
        User user = new User();
        user.setVerified(false);
        user.setOTPLimit(new Date(System.currentTimeMillis() + 10000)); // Set OTPLimit to a future date

        // Mock the UserRepository behavior
        when(userRepository.findUserByOTP(anyString())).thenReturn(Optional.of(user));

        // Call the verifyUser method
        authenticationService.verifyUser("valid_code");

        // Verify that the user is verified and the necessary methods are called
        assertTrue(user.isVerified());
        assertNull(user.getOTP());
        assertNull(user.getOTPLimit());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testVerifyUser_InvalidCode() {
        // Mock the UserRepository behavior
        when(userRepository.findUserByOTP(anyString())).thenReturn(Optional.empty());

        // Verify that UserNotFoundException is thrown
        assertThrows(UserNotFoundException.class, () -> authenticationService.verifyUser("invalid_code"));
        verify(userRepository, never()).save(any(User.class));

    }

    @Test
    public void testVerifyUser_CodeExpired() {
        // Create a mock User object with an expired OTPLimit
        User user = new User();
        user.setVerified(false);
        user.setOTPLimit(new Date(System.currentTimeMillis() - 10000)); // Set OTPLimit to a past date

        // Mock the UserRepository behavior
        when(userRepository.findUserByOTP(anyString())).thenReturn(Optional.of(user));

        // Verify that AccessDeniedException is thrown
        assertThrows(AccessDeniedException.class, () -> authenticationService.verifyUser("expired_code"));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testVerifyUser_AlreadyVerified() {
        // Create a mock User object with isVerified set to true
        User user = new User();
        user.setVerified(true);
        // Mock the UserRepository behavior
        when(userRepository.findUserByOTP(anyString())).thenReturn(Optional.of(user));

        // Verify that APIException is thrown
        assertThrows(APIException.class, () -> authenticationService.verifyUser("already_verified_code"));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testChangePassword_ValidPassword_PasswordUpdated() {
        // Arrange
        String email = "test@example.com";
        String newPassword = "newPassword123";
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        changePasswordDTO.setOldPassword("oldPassword");
        changePasswordDTO.setNewPassword(newPassword);
        User user = new User();
        user.setEmail(email);
        user.setPassword("oldPassword");

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(newPassword)).thenReturn("encodedNewPassword");

        // Act
        authenticationService.changePassword(changePasswordDTO);

        // Assert
        verify(userRepository, times(1)).findByEmail(email);
        verify(passwordEncoder, times(1)).encode(newPassword);
        verify(userRepository, times(1)).save(user);
        assertEquals("encodedNewPassword", user.getPassword());
    }

    @Test
    void testChangePassword_InvalidPassword_ThrowsUserNotFoundException() {
        // Arrange
        String email = "test@example.com";
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> authenticationService.changePassword(changePasswordDTO));

        verify(userRepository, times(1)).findByEmail(email);
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testLogout_AuthenticationSetToNull() {
        // Arrange
        Authentication authentication = new UsernamePasswordAuthenticationToken("test@example.com", null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Act
        authenticationService.logout();

        // Assert
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testResetPassword_ValidCodeAndPassword_UserPasswordReset() {
        // Arrange
        String code = "123456";
        ResetPasswordDTO resetPasswordRequest = new ResetPasswordDTO();
        resetPasswordRequest.setNewPassword("newPassword");

        User user = new User();
        user.setOTP(code);
        user.setPassword("oldPassword");

        when(userRepository.findUserByOTP(code)).thenReturn(Optional.of(user));

        when(passwordEncoder.encode(resetPasswordRequest.getNewPassword())).thenReturn("encodedPassword");

        // Act
        authenticationService.resetPassword(code, resetPasswordRequest);

        // Assert
        assertNull(user.getOTP());
        assertNull(user.getOTPLimit());
        assertEquals("encodedPassword", user.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testResetPassword_InvalidCode_ThrowsUserNotFoundException() {
        // Arrange
        String code = "invalidCode";
        ResetPasswordDTO resetPasswordRequest = new ResetPasswordDTO();

        when(userRepository.findUserByOTP(code)).thenReturn(Optional.empty());


        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> authenticationService.resetPassword(code, resetPasswordRequest));
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void testRequestResetPassword_UserFound_SendsResetPasswordEmail() throws MessagingException {
        // Arrange
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setEmail("test@example.com");
        User user = new User();
        user.setEmail(resetPasswordRequest.getEmail());

        when(userRepository.findByEmail(resetPasswordRequest.getEmail())).thenReturn(Optional.of(user));
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> otpCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> purposeCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> endpointCaptor = ArgumentCaptor.forClass(String.class);

        // Act
        authenticationService.requestResetPassword(resetPasswordRequest);

        // Assert
        verify(userRepository, times(1)).findByEmail(resetPasswordRequest.getEmail());
        verify(emailService, times(1)).sendVerificationEmail(emailCaptor.capture(),
                otpCaptor.capture(), purposeCaptor.capture(), endpointCaptor.capture());
        assertEquals(user.getEmail(), emailCaptor.getValue());

        assertEquals("reset-password", endpointCaptor.getValue());
        assertEquals("Reset Password", purposeCaptor.getValue());
    }

    @Test
    void testRequestResetPassword_UserNotFound_ThrowsUserNotFoundException() throws MessagingException {
        // Arrange
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setEmail("test@example.com");


        when(userRepository.findByEmail(resetPasswordRequest.getEmail())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> authenticationService.requestResetPassword(resetPasswordRequest));

        verify(userRepository, times(1)).findByEmail(resetPasswordRequest.getEmail());
        verify(emailService, times(0)).sendVerificationEmail(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void testRequestVerify_UserFound_SendsVerificationEmail() throws MessagingException {
        // Arrange
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        User user = new User();
        user.setEmail(resetPasswordRequest.getEmail());

        when(userRepository.findByEmail(resetPasswordRequest.getEmail())).thenReturn(Optional.of(user));
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> otpCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> purposeCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> endpointCaptor = ArgumentCaptor.forClass(String.class);

        // Act
        authenticationService.requestVerify(resetPasswordRequest);

        // Assert
        verify(userRepository, times(1)).findByEmail(resetPasswordRequest.getEmail());
        verify(emailService, times(1)).sendVerificationEmail(emailCaptor.capture(),
                otpCaptor.capture(), purposeCaptor.capture(), endpointCaptor.capture());
        assertEquals(user.getEmail(), emailCaptor.getValue());

        assertEquals("verify", endpointCaptor.getValue());
        assertEquals("Verification", purposeCaptor.getValue());
    }

    @Test
    void testRequestVerify_UserNotFound_ThrowsUserNotFoundException() throws MessagingException {
        // Arrange
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setEmail("test@example.com");


        when(userRepository.findByEmail(resetPasswordRequest.getEmail())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> authenticationService.requestVerify(resetPasswordRequest));

        verify(userRepository, times(1)).findByEmail(resetPasswordRequest.getEmail());
        verify(emailService, times(0)).sendVerificationEmail(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void testVerifyUserAndChangeMail_ValidCode_ChangesEmail() {
        // Arrange
        String code = "validCode";
        User user = new User();
        user.setOTP(code);
        user.setOTPLimit(new Date(System.currentTimeMillis() + 10000)); // Set OTPLimit to future time
        user.setTempEmail("newemail@example.com");

        when(userRepository.findUserByOTP(code)).thenReturn(Optional.of(user));

        // Act
        authenticationService.verifyUserAndChangeMail(code);

        // Assert
        verify(userRepository, times(1)).findUserByOTP(code);
        verify(userRepository, times(1)).save(user);

        assertNull(user.getOTP());
        assertNull(user.getOTPLimit());
        assertEquals("newemail@example.com", user.getEmail());
        assertNull(user.getTempEmail());
    }

    @Test
    void testVerifyUserAndChangeMail_InvalidCode_ThrowsUserNotFoundException() {
        // Arrange
        String code = "invalidCode";

        when(userRepository.findUserByOTP(code)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> authenticationService.verifyUserAndChangeMail(code));

        verify(userRepository, times(1)).findUserByOTP(code);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testVerifyUserAndChangeMail_CodeExpired_ThrowsAccessDeniedException() {
        // Arrange
        String code = "validCode";
        User user = new User();
        user.setOTP(code);
        user.setOTPLimit(new Date(System.currentTimeMillis() - 10000)); // Set OTPLimit to past time

        when(userRepository.findUserByOTP(code)).thenReturn(Optional.of(user));

        // Act and Assert
        assertThrows(AccessDeniedException.class, () -> authenticationService.verifyUserAndChangeMail(code));

        verify(userRepository, times(1)).findUserByOTP(code);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testOAuth2Register_SuccessfullyRegistersUser() {
        // Arrange
        RegisterRequest request = new RegisterRequest();
        request.setUsername("testuser");
        request.setPassword("password123");
        request.setEmail("test@example.com");

        // Act
        authenticationService.oAuth2register(request);

        // Assert
        verify(userRepository, times(1)).save(any(User.class));
        verify(cartRepository, times(1)).save(any(Cart.class));
        verify(wishlistRepository, times(1)).save(any(Wishlist.class));

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertEquals(request.getUsername(), capturedUser.getUsername());
//        System.out.println(passwordEncoder.encode(request.getPassword()));
//        assertTrue(passwordEncoder.matches(request.getPassword(), capturedUser.getPassword()));
        assertEquals(request.getEmail(), capturedUser.getEmail());
        assertEquals(UserRole.ROLE_USER, capturedUser.getRole());
        assertTrue(capturedUser.isVerified());
    }

}