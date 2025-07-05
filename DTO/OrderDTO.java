// src/main/java/com/example/demo/DTO/OrderDTO.java
package com.example.demo.DTO;

import java.util.List;

public class OrderDTO {
    private Long id;
    private Long customerId;
    private List<Long> itemIds;
    private Double total;
    // getters & setters...
}

