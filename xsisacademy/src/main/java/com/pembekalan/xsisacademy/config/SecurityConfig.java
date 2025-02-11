package com.pembekalan.xsisacademy.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.pembekalan.xsisacademy.service.implementation.UserServiceImpl;
import com.pembekalan.xsisacademy.util.JwtUtil;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private UserServiceImpl userAuthServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                // .authorizeHttpRequests(auth -> auth
                // .requestMatchers("/api/login", "/api/register", "/api/logout").permitAll()
                // .anyRequest().authenticated() // Blokir endpoint lain tanpa autentikasi
                // )
                // .rememberMe(rememberMe -> rememberMe
                // .alwaysRemember(true)
                // .tokenValiditySeconds(86400)
                // .useSecureCookie(true))
                .addFilterBefore(customRateLimiterFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(dynamicAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(apiLoggingFilter(), JwtRequestFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }

    @Bean
    JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter(jwtUtil, userAuthServiceImpl);
    }

    @Bean
    DynamicAuthorizationFilter dynamicAuthorizationFilter() {
        return new DynamicAuthorizationFilter();
    }

    @Bean
    CustomRateLimiterFilter customRateLimiterFilter() {
        return new CustomRateLimiterFilter();
    }

    @Bean
    ApiLoggingFilter apiLoggingFilter() {
        return new ApiLoggingFilter();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); // URL SvelteKit
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.addExposedHeader("Vary");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
