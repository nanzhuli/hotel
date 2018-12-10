package com.hotel.service;

import com.hotel.model.orderroom;
import com.hotel.repository.orderroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@Service
public class orderroomService {
    @Autowired
    orderroomRepository orderroomrepository;

    public List<orderroom> findAll(int orderno) {
        orderroom or = new orderroom();
        or.setOrderno(orderno);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("orderno",
                ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("orno","roomno",
                "brand");
        Example<orderroom> ex = Example.of(or, exampleMatcher);
        List<orderroom> worker = orderroomrepository.findAll(ex);
        return worker;
    }

    public orderroom findById(int o) {
        return orderroomrepository.findById(o).orElse(null);
    }

    public orderroom findOne(int rino) {
        return orderroomrepository.findById(rino).orElse(null);
    }

    public orderroom save(orderroom e) {
        return orderroomrepository.save(e);
    }
}
