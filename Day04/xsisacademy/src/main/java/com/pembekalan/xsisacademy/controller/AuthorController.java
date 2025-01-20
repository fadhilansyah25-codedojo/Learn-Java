package com.pembekalan.xsisacademy.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pembekalan.xsisacademy.dto.request.AuthorRequestDto;
import com.pembekalan.xsisacademy.dto.response.AuthorResponseDto;
import com.pembekalan.xsisacademy.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping("")
    public ResponseEntity<?> getAllAuthors() {
        List<AuthorResponseDto> authorResponseDtos = authorService.getAllAuthors();

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", "200");
        resultMap.put("message", "success");
        resultMap.put("data", authorResponseDtos);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Integer id) {
        AuthorResponseDto authorResponseDto = authorService.getAuthorById(id);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", "200");
        resultMap.put("message", "success");
        resultMap.put("data", authorResponseDto);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveAuthor(@RequestBody AuthorRequestDto authorRequestDto) {

        AuthorResponseDto authorResponseDto = authorService.saveAuthor(authorRequestDto);

        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();

        resultMap.put("status", "200");
        resultMap.put("message", "success");
        resultMap.put("data", authorResponseDto);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editAuthor(@RequestBody AuthorRequestDto authorRequestDto) {

        AuthorResponseDto authorResponseDto = authorService.saveAuthor(authorRequestDto);

        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();

        resultMap.put("status", "200");
        resultMap.put("message", "success");
        resultMap.put("data", authorResponseDto);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Integer id) {
        authorService.deleteAuthorById(id);
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();

        resultMap.put("status", "200");
        resultMap.put("message", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
