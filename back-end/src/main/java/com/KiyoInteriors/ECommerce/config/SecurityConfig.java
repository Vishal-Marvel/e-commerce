package com.KiyoInteriors.ECommerce.config;

import com.KiyoInteriors.ECommerce.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import com.KiyoInteriors.ECommerce.security.JWTAuthEntryPoint;
import com.KiyoInteriors.ECommerce.security.JWTAuthFilter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig
{
    private final JWTAuthFilter authFilter;
    private final JWTAuthEntryPoint authEntryPoint;

    @Bean
    SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/admin/**").hasAuthority(UserRole.ROLE_ADMIN.name())
                        .requestMatchers("/user/**").hasAuthority(UserRole.ROLE_USER.name())
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
//                .addFilterAfter(authFilter, ExceptionTranslationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}