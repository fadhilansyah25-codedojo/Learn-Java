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

import com.pembekalan.xsisacademy.service.implementation.UserAuthServiceImpl;
import com.pembekalan.xsisacademy.util.JwtUtil;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private UserAuthServiceImpl userAuthServiceImpl;

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
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> {
                    try {
                        csrf.disable().authorizeHttpRequests(auth -> auth.requestMatchers("/api/login", "/api/register")
                                .permitAll().anyRequest().authenticated());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .rememberMe(rememberMe -> rememberMe
                        .alwaysRemember(true)
                        .tokenValiditySeconds(86400)
                        .useSecureCookie(true));

        http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter(jwtUtil, userAuthServiceImpl);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
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
