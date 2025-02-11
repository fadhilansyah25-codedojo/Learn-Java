package com.pembekalan.xsisacademy.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pembekalan.xsisacademy.service.implementation.UserServiceImpl;
import com.pembekalan.xsisacademy.util.JwtUtil;

import java.io.IOException;
// import java.util.List;

@Component
@AllArgsConstructor
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImpl userAuthServiceImpl;

    // private final List<String> allowedEndpoint = List.of("/api/register", "/api/login", "/api/logout");

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // String requestURI = request.getRequestURI();

        // // Skip filter untuk endpoint register && login
        // if (allowedEndpoint.contains(requestURI)) {
        //     filterChain.doFilter(request, response);
        //     return;
        // }

        String username = null;
        String jwt = null;
        Cookie[] cookies = request.getCookies();

        // Cek cookie pada header
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }

        if (jwt != null) {
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userAuthServiceImpl.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails)) {
                // Set autentikasi di SecurityContext
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // Token tidak valid
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid Token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
