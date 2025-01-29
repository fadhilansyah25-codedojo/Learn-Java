package com.pembekalan.xsisacademy.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CategoryResponseDto {
    private Integer id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private Boolean isDeleted;
}
