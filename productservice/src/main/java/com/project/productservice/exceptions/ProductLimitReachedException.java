package com.project.productservice.exceptions;

public class ProductLimitReachedException extends Exception {
    public ProductLimitReachedException(String s) {
        super(s);
    }
}
