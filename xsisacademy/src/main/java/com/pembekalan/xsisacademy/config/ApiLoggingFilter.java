package com.pembekalan.xsisacademy.config;

import java.io.IOException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(ApiLoggingFilter.class);

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // log request details
        logger.info("[REQUEST] {} {} | Headers: {}",
                request.getMethod(),
                request.getRequestURI(),
                getHeaders(request));

        filterChain.doFilter(request, response);

        logger.info("[RESPONSE] {} {} | Status: {} | Headers: {}",
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                getResponseHeaders(response));
    }

    private String getHeaders(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (!headerName.equalsIgnoreCase("Authorization") && !headerName.equalsIgnoreCase("Cookie")) {
                headers.append(headerName).append(": ").append(request.getHeader(headerName)).append("; ");
            }
        }

        return headers.toString();
    }

    private String getResponseHeaders(HttpServletResponse response) {
        StringBuilder headers = new StringBuilder();
        response.getHeaderNames().forEach(headerName -> {

            if (!headerName.equalsIgnoreCase("Set-Cookie")) {
                headers.append(headerName).append(": ").append(response.getHeader(headerName)).append("; ");
            }
        });

        return headers.toString();
    }
}
