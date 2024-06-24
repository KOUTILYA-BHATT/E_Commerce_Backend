package com.project.productservice.inheritancedemo.joinedtables;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_it")
public class Instructor extends User {
    private String specialisation;
}