package com.pembekalan.xsisacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pembekalan.xsisacademy.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
