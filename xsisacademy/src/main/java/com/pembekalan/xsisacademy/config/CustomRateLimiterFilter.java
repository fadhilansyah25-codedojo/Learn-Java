package com.pembekalan.xsisacademy.config;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomRateLimiterFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    private Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.classic(1, Refill.greedy(1, Duration.ofSeconds(1)));
        return Bucket.builder().addLimit(limit).build();
    }

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Get user identifier (IP or authenticated username)
        String identifier = getUserIdentifier(request);

        // Get or create a rate-limited bucket for identifier
        Bucket bucket = cache.computeIfAbsent(identifier, k -> createNewBucket());

        // check if request is allowed
        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response); // Allow request
            return;
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType("application/json");
            response.getWriter().write("Too many requests - Try again later.");
            return;
        }

    }

    private String getUserIdentifier(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            return auth.getName();
        } else {
            return request.getRemoteAddr();
        }
    }
}
