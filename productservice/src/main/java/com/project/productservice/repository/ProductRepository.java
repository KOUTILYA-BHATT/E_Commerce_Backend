package com.project.productservice.repository;

import com.project.productservice.models.Product;
import com.project.productservice.projections.ProductWithTitleandDesc;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findById(Long id);

    void delete(Product product);

    @Override
    Product save(Product product); // create and update

    //HQL
    @Query("select p.title as title, p.description as description from Product p where p.id=:id")
    ProductWithTitleandDesc someRandomQuery(@Param("id") Long id);

    //SQL
    @Query(value = "select title, description from product where id = :id", nativeQuery = true)
    ProductWithTitleandDesc someRandomQuery1(@Param("id") Long id);

    List<Product> findByTitleContains(String title, Pageable pageable);
}
