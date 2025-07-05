// src/main/java/com/example/demo/entity/Order.java
package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders") // avoid SQL reserved word
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;

    private Double total;
    // getters & setters...
}
