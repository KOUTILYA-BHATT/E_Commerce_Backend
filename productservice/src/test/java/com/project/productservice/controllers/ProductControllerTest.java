package com.project.productservice.controllers;

import com.project.productservice.exceptions.ProductLimitReachedException;
import com.project.productservice.models.Product;
import com.project.productservice.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @MockBean
    ProductService productService;

    @Autowired
    ProductController productController;

    @BeforeEach
    void setup() {
        //Arrange
        Product product = new Product();
        product.setId(2L);
        product.setTitle("Dell Laptop");
        product.setDescription("Dell Laptop");
        product.setPrice(15.0);
        product.setQuantity(3);

        //Rule
        when(productService.getProductById(any(Long.class))).thenReturn(product);
    }

    @Test
    void Test_WhenGetProductByIdIsCalled_ThenReturnProduct() throws ProductLimitReachedException {

        //Act
        ResponseEntity<Product> responseEntity=  productController.getProductByID(2L);

        //Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(2L,responseEntity.getBody().getId());
        assertEquals("Dell Laptop",responseEntity.getBody().getTitle());
        assertEquals("Dell Laptop",responseEntity.getBody().getDescription());
        assertEquals(15.0,responseEntity.getBody().getPrice());
        assertEquals(3,responseEntity.getBody().getQuantity());
    }

    @Test
    void Test_WhenGetProductByIdIsCalled_ThenThrowRuntimeException() throws RuntimeException {
        //Rule
        when(productService.getProductById(any(Long.class))).
                thenThrow(new RuntimeException("Something went wrong"));

        assertThrows(RuntimeException.class,()->productController.getProductByID(2L));
    }

//    @Test
//    void Test_WhenGetProductByIdIsCalled_ThenCallRealProductService(){
//        when(productService.getProductById(any(Long.class))).thenCallRealMethod();
//    }
    @Test
    void getAllProducts() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void replaceProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProductbyId() {
    }
}