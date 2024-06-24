package com.project.productservice.controllers;

import com.project.productservice.dtos.SearchRequestDto;
import com.project.productservice.models.Product;
import com.project.productservice.services.SearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @PostMapping
    public List<Product> search(@RequestBody SearchRequestDto searchRequestDto){
        return searchService.searchProduct(searchRequestDto.getKeyword(), searchRequestDto.getPageNumber(),searchRequestDto.getSizeOfPage());
    }
}
