package com.project.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends Base{
        private String title;
        private double price;
        @ManyToOne(cascade = CascadeType.PERSIST)
        private Category category;
        private String description;
        private int quantity;
        private Boolean available;
        private Boolean isDeleted;
}
