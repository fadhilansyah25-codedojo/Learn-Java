package com.pembekalan.xsisacademy.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pembekalan.xsisacademy.service.implementation.UserAuthServiceImpl;
import com.pembekalan.xsisacademy.util.JwtUtil;

import java.io.IOException;

@Component
@AllArgsConstructor
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthServiceImpl userAuthServiceImpl;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // Skip filter untuk endpoint register && login
        if ("/api/register".equals(requestURI) || "/api/login".equals(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        Cookie[] cookies = request.getCookies();

        // Cek cookie jika header Authorization tidak ada
        if (authorizationHeader == null && cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        } else if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
        }

        if (jwt != null) {
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userAuthServiceImpl.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication
                        .setDetails(new WebAuthenticationDetailsSource()
                                .buildDetails(request));

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}
