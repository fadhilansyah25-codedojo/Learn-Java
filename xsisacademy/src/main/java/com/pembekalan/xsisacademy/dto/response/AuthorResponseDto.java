package com.pembekalan.xsisacademy.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AuthorResponseDto {
    private Integer id;
    private String name;
    private Integer publishedBooks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isDeleted;
}
