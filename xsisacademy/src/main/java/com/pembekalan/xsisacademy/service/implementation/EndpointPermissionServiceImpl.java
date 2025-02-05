package com.pembekalan.xsisacademy.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import com.pembekalan.xsisacademy.entity.EndpointPermission;
import com.pembekalan.xsisacademy.repository.EndpointPermissionRepository;
import com.pembekalan.xsisacademy.service.EndpointPermissionService;

@Service
public class EndpointPermissionServiceImpl implements EndpointPermissionService {

    @Autowired
    private EndpointPermissionRepository endpointPermissionRepo;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    public List<EndpointPermission> findMatchingPermissions(String method, String uri) {
        // Ambil semua permission untuk method yang sesuai
        List<EndpointPermission> permissions = endpointPermissionRepo.findByHttpMethod(method);

        // Filter permission yang cocok dengan URI
        return permissions.stream()
                .filter(perm -> pathMatcher.match(perm.getUrlPattern(), uri))
                .collect(Collectors.toList());
    }

}
