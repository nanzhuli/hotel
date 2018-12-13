package com.hotel.service;

import com.hotel.model.OrderRoom;
import com.hotel.repository.OrderRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRoomService
{
    @Autowired
    OrderRoomRepository orderroomrepository;

    public List<OrderRoom> findAll(int orderno) {
        OrderRoom or = new OrderRoom();
        or.setOrderno(orderno);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("orderno",
                ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("orno","roomno",
                "brand");
        Example<OrderRoom> ex = Example.of(or, exampleMatcher);
        return orderroomrepository.findAll(ex);
    }

    public OrderRoom findById(int o) {
        return orderroomrepository.findById(o).orElse(null);
    }

    public OrderRoom findOne(int rino) {
        return orderroomrepository.findById(rino).orElse(null);
    }

    public OrderRoom save(OrderRoom e) {
        return orderroomrepository.save(e);
    }

}
