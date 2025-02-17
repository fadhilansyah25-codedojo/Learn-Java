package com.pembekalan.xsisacademy.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pembekalan.xsisacademy.dto.request.BookRequestDto;
import com.pembekalan.xsisacademy.dto.response.BookResponseDto;
import com.pembekalan.xsisacademy.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("")
    public ResponseEntity<?> getAllBooks() {
        List<BookResponseDto> bookResponseDtos = bookService.getAllBooks();

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", "200");
        resultMap.put("message", "success");
        resultMap.put("data", bookResponseDtos);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getBookPerPage(
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", required = false) String sortBy) {

        if (null == offset)
            offset = 0;
        if (null == pageSize)
            pageSize = 10;
        if (StringUtils.isEmpty(sortBy))
            sortBy = "id";

        Page<BookResponseDto> bookResponseDtoPages = bookService
                .getBooksPage(PageRequest.of(offset, pageSize, Sort.by(sortBy)));

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", "200");
        resultMap.put("message", "success");
        resultMap.put("data", bookResponseDtoPages);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Integer id) {
        BookResponseDto bookResponseDtos = bookService.getBookById(id);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", "200");
        resultMap.put("message", "success");
        resultMap.put("data", bookResponseDtos);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveBook(@RequestBody BookRequestDto bookRequestDto) {

        BookResponseDto bookResponseDto = bookService.saveBook(bookRequestDto);

        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();

        resultMap.put("status", "200");
        resultMap.put("message", "success");
        resultMap.put("data", bookResponseDto);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> editBook(@RequestBody BookRequestDto bookRequestDto) {

        BookResponseDto bookResponseDto = bookService.saveBook(bookRequestDto);

        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();

        resultMap.put("status", "200");
        resultMap.put("message", "success");
        resultMap.put("data", bookResponseDto);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
        bookService.deleteBookById(id);
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();

        resultMap.put("status", "200");
        resultMap.put("message", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
