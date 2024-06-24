package com.project.productservice.controllers;

import com.project.productservice.exceptions.ProductLimitReachedException;
import com.project.productservice.models.Product;
import com.project.productservice.services.ProductService;
import com.project.productservice.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private TokenService tokenService;

    @Autowired
    public ProductController(ProductService productService, TokenService tokenService) {
        this.productService = productService;
        this.tokenService = tokenService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable("id") Long id) throws ProductLimitReachedException {
//        if(id>100){
//            throw new ProductLimitReachedException("There can be max 100 items");
//        }
//        if(!tokenService.validateToken(token)){
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
//        try {
            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id,product);
    }
    @DeleteMapping("/{id}")
    public Product deleteProductbyId(@PathVariable("id") Long id) {
        return new Product();
    }
}
