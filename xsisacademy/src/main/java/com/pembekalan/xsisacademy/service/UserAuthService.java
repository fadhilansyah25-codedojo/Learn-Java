package com.pembekalan.xsisacademy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pembekalan.xsisacademy.dto.request.RegistrationRequestDto;
import com.pembekalan.xsisacademy.entity.UserAuth;
import com.pembekalan.xsisacademy.repository.UserAuthRepository;

@Service
public class UserAuthService {
    @Autowired
    private UserAuthRepository userAuthRepository;

    public void registerUser(RegistrationRequestDto requestDto) {
        UserAuth userAuth = new UserAuth();
        userAuth.setUsername(requestDto.getUsername());
        userAuth.setEmail(requestDto.getEmail());
        userAuth.setPassword(requestDto.getPassword());
        userAuthRepository.save(userAuth);
    }
}
