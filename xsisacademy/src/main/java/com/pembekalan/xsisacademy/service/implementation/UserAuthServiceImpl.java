package com.pembekalan.xsisacademy.service.implementation;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pembekalan.xsisacademy.entity.UserAuth;
import com.pembekalan.xsisacademy.repository.UserAuthRepository;

@Service
public class UserAuthServiceImpl implements UserDetailsService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth userAuth = userAuthRepository.findByUsername(username);
        if (userAuth == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                userAuth.getUsername(),
                userAuth.getPassword(),
                Collections.emptyList());
    }

}