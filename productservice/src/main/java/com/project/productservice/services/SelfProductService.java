package com.project.productservice.services;

import com.project.productservice.models.Category;
import com.project.productservice.models.Product;
import com.project.productservice.repository.CategoryRepository;
import com.project.productservice.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService{

    private final CategoryRepository categoryRepository;
    ProductRepository productRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        Category category= product.getCategory();
        if(category.getId() == null) {
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        }
// categoryRepo.findByTitle(product.getCategory().getTitle)
        Product newProduct = productRepository.save(product);
        return newProduct;
    }

    @Override
    public Product deleteProduct() {
        return null;
    }
}
