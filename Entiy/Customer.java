// src/main/java/com/example/demo/entity/Customer.java
package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    // Relationships
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;
    // getters & setters...
}

