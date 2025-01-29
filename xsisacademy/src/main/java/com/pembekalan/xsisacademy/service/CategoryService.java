package com.pembekalan.xsisacademy.service;

import java.util.List;

import com.pembekalan.xsisacademy.dto.request.CategoryRequestDto;
import com.pembekalan.xsisacademy.dto.response.CategoryResponseDto;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategoryById(Integer id);

    CategoryResponseDto saveCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto editCategoryById(CategoryRequestDto categoryRequestDto, Integer id);

    void deleteCategoryById(Integer id);
}
