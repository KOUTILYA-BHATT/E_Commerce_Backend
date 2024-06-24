package com.project.productservice.inheritancedemo.joinedtables;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_mentor")
public class Mentor extends User {
    private String compName;
    private double avgrating;
}
