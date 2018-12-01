package com.hotel.service;

import com.hotel.model.Order;
import com.hotel.repository.orderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class orderService {
    @Autowired
    orderRepository orderrepository;

    public List<Order> findAll() {return orderrepository.findAll();}

    public Order findByOrderno(int orderno) {
        return orderrepository.findById(orderno).orElse(null);
    }

    public Order save(Order r) {
        return orderrepository.save(r);
    }

    public void delete(Order r) {
        orderrepository.delete(r);
    }

}
