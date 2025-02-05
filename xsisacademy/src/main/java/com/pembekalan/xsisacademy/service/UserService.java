package com.pembekalan.xsisacademy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pembekalan.xsisacademy.dto.request.RegistrationRequestDto;
import com.pembekalan.xsisacademy.entity.User;
import com.pembekalan.xsisacademy.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userAuthRepository;

    public void registerUser(RegistrationRequestDto requestDto) {
        User userAuth = new User();
        userAuth.setUsername(requestDto.getUsername());
        userAuth.setEmail(requestDto.getEmail());
        userAuth.setPassword(requestDto.getPassword());
        userAuthRepository.save(userAuth);
    }
}
