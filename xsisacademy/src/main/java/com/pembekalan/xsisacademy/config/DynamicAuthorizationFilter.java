package com.pembekalan.xsisacademy.config;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pembekalan.xsisacademy.entity.EndpointPermission;
import com.pembekalan.xsisacademy.service.EndpointPermissionService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class DynamicAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private EndpointPermissionService endpointPermissionService;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String httpMethod = request.getMethod();
        String requestUri = request.getRequestURI();

        // 1. Cari permission yang cocok untuk endpoint ini
        List<EndpointPermission> requiredPermissions = endpointPermissionService
                .findMatchingPermissions(httpMethod, requestUri);

        // 2. Jika tidak ada aturan, izinkan akses
        if (requiredPermissions.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Ambil permissions pengguna yang sedang login
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> userPermissions = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        for (String string : userPermissions) {
            System.out.println(string);
        }

        // 4. Cek apakah pengguna memiliki permission yang diperlukan
        boolean hasAccess = requiredPermissions.stream()
                .anyMatch(perm -> userPermissions.contains(perm.getRequiredPermission()));

        if (hasAccess) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Akses ditolak!");
        }
    }

}
