package com.project.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends Base{
    private String title;
//    @OneToMany(mappedBy = "category")
//    private List<Product> productList;
}
