package com.project.productservice.services;

import com.project.productservice.models.Product;
import com.project.productservice.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> searchProduct(String keyword, int pageNumber, int pageSize) {
        return productRepository.findByTitleContains(keyword, PageRequest.of(pageNumber, pageSize));
    }
}
