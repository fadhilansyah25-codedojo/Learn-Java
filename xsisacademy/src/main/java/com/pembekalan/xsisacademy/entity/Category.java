package com.pembekalan.xsisacademy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Category extends BaseEntity {

    public Category(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(length = 100)
    private String name;

    // private Boolean isDeleted = false;

    // @CreationTimestamp
    // @Temporal(TemporalType.TIMESTAMP)
    // @Column(updatable = false)
    // private LocalDateTime createdAt;

    // @UpdateTimestamp
    // @Temporal(TemporalType.TIMESTAMP)
    // private LocalDateTime updatedAt;
}