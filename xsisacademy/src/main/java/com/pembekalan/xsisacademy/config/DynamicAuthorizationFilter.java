package com.pembekalan.xsisacademy.config;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

        List<EndpointPermission> requiredPermissions = endpointPermissionService.findMatchingPermissions(httpMethod,
                requestUri);

        // 1️⃣ Jika tidak ada aturan, langsung izinkan akses
        if (requiredPermissions.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2️⃣ Ambil permissions pengguna
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized - Please log in");
            return;
        }

        Set<String> userPermissions = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        // 3️⃣ Cek apakah pengguna memiliki permission yang diperlukan
        boolean hasAccess = requiredPermissions.stream()
                .anyMatch(perm -> userPermissions.contains(perm.getRequiredPermission()));

        if (!hasAccess) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Access Denied!");
            return;
        }

        // 4️⃣ Jika semua cek lolos, lanjutkan request
        filterChain.doFilter(request, response);
    }

}
