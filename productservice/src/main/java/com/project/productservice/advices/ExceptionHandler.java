package com.project.productservice.advices;

import com.project.productservice.controllers.ProductController;
import com.project.productservice.dtos.ExceptionDto;
import com.project.productservice.exceptions.ProductLimitReachedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice(basePackageClasses = ProductController.class)
public class ExceptionHandler {

    //@org.springframework.web.bind.annotation.ExceptionHandler({RuntimeException.class, NullPointerException.class})
    public ResponseEntity<String> handleException() {
        System.out.print("Something went Wrong");
        return new ResponseEntity<>("Something went Wrong", HttpStatus.NOT_FOUND);
    }
    //@org.springframework.web.bind.annotation.ExceptionHandler({ProductLimitReachedException.class})
    public ResponseEntity<ExceptionDto> handleCustomException() {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Custom Something went Wrong");
        exceptionDto.setErrorCode("PRODUCT_LIMIT_REACHED");
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
}
