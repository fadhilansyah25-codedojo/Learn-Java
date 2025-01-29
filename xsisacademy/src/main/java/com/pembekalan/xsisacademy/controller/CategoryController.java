package com.pembekalan.xsisacademy.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pembekalan.xsisacademy.dto.request.CategoryRequestDto;
import com.pembekalan.xsisacademy.dto.response.CategoryResponseDto;
import com.pembekalan.xsisacademy.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategories() {
        List<CategoryResponseDto> categoryResponseDtos = categoryService.getAllCategories();

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", "200");
        resultMap.put("message", "success");
        resultMap.put("data", categoryResponseDtos);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(id);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", "200");
        resultMap.put("message", "success");
        resultMap.put("data", categoryResponseDto);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryRequestDto categoryRequestDto) {

        CategoryResponseDto categoryResponseDto = categoryService.saveCategory(categoryRequestDto);

        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();

        resultMap.put("status", "200");
        resultMap.put("message", "success");
        resultMap.put("data", categoryResponseDto);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCategory(@RequestBody CategoryRequestDto categoryRequestDto, @PathVariable Integer id) {

        CategoryResponseDto categoryResponseDto = categoryService.saveCategory(categoryRequestDto);

        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();

        resultMap.put("status", "200");
        resultMap.put("message", "success");
        resultMap.put("data", categoryResponseDto);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();

        resultMap.put("status", "200");
        resultMap.put("message", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
