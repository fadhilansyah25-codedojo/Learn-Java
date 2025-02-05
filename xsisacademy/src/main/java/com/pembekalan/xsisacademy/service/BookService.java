package com.pembekalan.xsisacademy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.pembekalan.xsisacademy.dto.request.BookRequestDto;
import com.pembekalan.xsisacademy.dto.response.BookResponseDto;

public interface BookService {
    List<BookResponseDto> getAllBooks();

    Page<BookResponseDto> getBooksPage(PageRequest pageRequest);

    BookResponseDto getBookById(Integer id);

    BookResponseDto saveBook(BookRequestDto bookRequestDto);

    BookResponseDto editBook(BookRequestDto bookRequestDto);

    void deleteBookById(Integer id);
}
