package com.pembekalan.xsisacademy.dto.response;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private T data;
    private String message;
    private String status;
}
