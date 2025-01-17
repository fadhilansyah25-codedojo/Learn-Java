package com.pembekalan.xsisacademy.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Book extends BaseEntity {

    public Book(Author author, Category category, Publisher publisher, String title, String synopsis, Integer stock, Date publishedAt) {
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.title = title;
        this.synopsis = synopsis;
        this.stock = stock;
        this.publishedAt = publishedAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @Column(length = 100)
    private String title;

    @Column
    private String synopsis;

    private Integer stock;

    private Date publishedAt;
}
