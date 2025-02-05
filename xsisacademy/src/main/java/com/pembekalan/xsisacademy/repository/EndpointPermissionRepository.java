package com.pembekalan.xsisacademy.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pembekalan.xsisacademy.entity.EndpointPermission;

public interface EndpointPermissionRepository extends JpaRepository<EndpointPermission, Long> {
        // @Cacheable("endpointPermissions")
        // @Query("SELECT ep FROM EndpointPermission ep WHERE ep.httpMethod = :method
        // AND :uri LIKE ep.urlPattern")
        // @Query(value = "SELECT * FROM endpoint_permissions ep WHERE LOWER(ep.http_method) = LOWER(:method) AND :uri ~ REPLACE(ep.url_pattern, E'/\\\\*\\\\*$', '/.*')", nativeQuery = true)
        // List<EndpointPermission> findByHttpMethodAndMatchingUrlPattern(
        //                 @Param("method") String method,
        //                 @Param("uri") String uri);

        @Cacheable("endpointPermissions")
        @Query("SELECT ep FROM EndpointPermission ep WHERE ep.httpMethod = :method")
        List<EndpointPermission> findByHttpMethod(@Param("method") String method);
}