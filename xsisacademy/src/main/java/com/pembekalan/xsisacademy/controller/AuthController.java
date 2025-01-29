package com.pembekalan.xsisacademy.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pembekalan.xsisacademy.dto.request.AuthenticationRequestDto;
import com.pembekalan.xsisacademy.entity.UserAuth;
import com.pembekalan.xsisacademy.repository.UserAuthRepository;
import com.pembekalan.xsisacademy.service.implementation.UserAuthServiceImpl;
import com.pembekalan.xsisacademy.util.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAuthServiceImpl userAuthServiceImpl;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserAuth userAuth) {
        userAuth.setPassword(passwordEncoder.encode(userAuth.getPassword()));

        userAuthRepository.save(userAuth);
        return "User Authentication has been created";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequestDto authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));

            final UserDetails userDetails = userAuthServiceImpl.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, createCookie(jwt))
                    .body(Map.of("status", "success", "message", "Authenticated"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", "error", "message", "Invalid credentials"));
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        String jwt = null;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }

        if (jwt == null || !jwtUtil.validateToken(jwt)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok().build();
    }

    private String createCookie(String token) {
        return ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(true) // Hanya di production
                .path("/")
                .maxAge(15 * 60) // 15 menit
                // .sameSite("Strict")
                .sameSite("None")
                .build()
                .toString();
    }
}
