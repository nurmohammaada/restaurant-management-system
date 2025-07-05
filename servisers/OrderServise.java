// src/main/java/com/example/demo/service/OrderService.java
package com.example.demo.service;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final CustomerRepository custRepo;
    private final ItemRepository itemRepo;

    public OrderService(OrderRepository orderRepo,
                        CustomerRepository custRepo,
                        ItemRepository itemRepo) {
        this.orderRepo = orderRepo;
        this.custRepo = custRepo;
        this.itemRepo = itemRepo;
    }

    @Transactional
    public OrderDTO create(OrderDTO dto) {
        Customer customer = custRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        List<Item> items = itemRepo.findAllById(dto.getItemIds());
        double total = items.stream().mapToDouble(Item::getPrice).sum();

        Order order = new Order();
        order.setCustomer(customer);
        order.setItems(items);
        order.setTotal(total);
        Order saved = orderRepo.save(order);

        OrderDTO result = new OrderDTO();
        result.setId(saved.getId());
        result.setCustomerId(customer.getId());
        result.setItemIds(items.stream().map(Item::getId).collect(Collectors.toList()));
        result.setTotal(total);
        return result;
    }

    public List<OrderDTO> listByCustomer(Long customerId) {
        return orderRepo.findByCustomerId(customerId)
                .stream()
                .map(o -> {
                    OrderDTO dto = new OrderDTO();
                    dto.setId(o.getId());
                    dto.setCustomerId(o.getCustomer().getId());
                    dto.setItemIds(o.getItems().stream().map(Item::getId).collect(Collectors.toList()));
                    dto.setTotal(o.getTotal());
                    return dto;
                }).collect(Collectors.toList());
    }
}
