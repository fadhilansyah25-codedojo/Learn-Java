package com.pembekalan.xsisacademy.service;

import java.util.List;

import com.pembekalan.xsisacademy.dto.request.BookRequestDto;
import com.pembekalan.xsisacademy.dto.response.BookResponseDto;

public interface BookService {
    List<BookResponseDto> getAllBooks();

    BookResponseDto getBookById(Integer id);

    BookResponseDto saveBook(BookRequestDto bookRequestDto);

    BookResponseDto editBook(BookRequestDto bookRequestDto);

    void deleteBookById(Integer id);
}
