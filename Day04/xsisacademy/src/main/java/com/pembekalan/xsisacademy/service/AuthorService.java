package com.pembekalan.xsisacademy.service;

import java.util.List;

import com.pembekalan.xsisacademy.dto.request.AuthorRequestDto;
import com.pembekalan.xsisacademy.dto.response.AuthorResponseDto;

public interface AuthorService {
    List<AuthorResponseDto> getAllAuthors();

    AuthorResponseDto getAuthorById(Integer id);

    AuthorResponseDto saveAuthor(AuthorRequestDto authorRequestDto);

    AuthorResponseDto editAuthor(AuthorRequestDto authorRequestDto, Integer id);

    void deleteAuthorById(Integer id);
}
