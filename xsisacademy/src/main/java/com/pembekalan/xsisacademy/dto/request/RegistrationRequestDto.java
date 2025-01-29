package com.pembekalan.xsisacademy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDto {
    private String username;
    private String email;
    private String password;
    // Constructors, getters, and setters
}
