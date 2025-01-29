package com.pembekalan.xsisacademy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pembekalan.xsisacademy.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query(value = "SELECT a FROM Author a ORDER BY a.createdAt DESC")
    List<Author> getAllAuthors();
}