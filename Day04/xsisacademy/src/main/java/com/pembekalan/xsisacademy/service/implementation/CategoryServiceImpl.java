package com.pembekalan.xsisacademy.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pembekalan.xsisacademy.dto.request.CategoryRequestDto;
import com.pembekalan.xsisacademy.dto.response.CategoryResponseDto;
import com.pembekalan.xsisacademy.entity.Category;
import com.pembekalan.xsisacademy.repository.CategoryRepository;
import com.pembekalan.xsisacademy.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(category -> modelMapper().map(category, CategoryResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElse(null);

        return modelMapper().map(category, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto saveCategory(CategoryRequestDto categoryRequestDto) {
        Category category = modelMapper().map(categoryRequestDto, Category.class);

        categoryRepository.save(category);

        return modelMapper().map(category, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto editCategoryById(CategoryRequestDto categoryRequestDto, Integer id) {
        Category category = modelMapper().map(categoryRequestDto, Category.class);

        category.setId(id);

        categoryRepository.save(category);

        return modelMapper().map(category, CategoryResponseDto.class);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }

}
