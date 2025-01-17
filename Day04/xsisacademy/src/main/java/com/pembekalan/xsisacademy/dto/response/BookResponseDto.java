package com.pembekalan.xsisacademy.dto.response;

import java.util.Date;

import com.pembekalan.xsisacademy.entity.Author;
import com.pembekalan.xsisacademy.entity.Category;
import com.pembekalan.xsisacademy.entity.Publisher;

import lombok.Data;

@Data
public class BookResponseDto {
    private Integer id;
    private Author author;
    private Category category;
    private Publisher publisher;
    private String title;
    private String synopsis;
    private Integer stock;
    private Date publishedAt;
}
