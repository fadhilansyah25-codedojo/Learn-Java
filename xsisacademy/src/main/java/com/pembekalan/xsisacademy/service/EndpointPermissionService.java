package com.pembekalan.xsisacademy.service;

import java.util.List;

import com.pembekalan.xsisacademy.entity.EndpointPermission;

public interface EndpointPermissionService {
    List<EndpointPermission> findMatchingPermissions(String method, String uri);
}
