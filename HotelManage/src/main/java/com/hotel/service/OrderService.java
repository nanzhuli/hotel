package com.hotel.service;

import com.hotel.model.Order;
import com.hotel.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService
{
    @Autowired
	OrderRepository orderrepository;

    public List<Order> findAll() {return orderrepository.findAll();}

    public Order findById(int o) {
        return orderrepository.findById(o).orElse(null);
    }

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
