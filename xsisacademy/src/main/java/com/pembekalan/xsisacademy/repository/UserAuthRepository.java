package com.pembekalan.xsisacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pembekalan.xsisacademy.entity.UserAuth;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
    UserAuth findByUsername(String username);
}
