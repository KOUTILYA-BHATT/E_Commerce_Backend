package com.project.productservice.repository;

import com.project.productservice.models.Category;
import com.project.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Override
    Category save(Category category); // create and update



}
