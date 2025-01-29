package com.pembekalan.xsisacademy.dto.request;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
