package com.hotel.service;

import com.hotel.model.room;
import com.hotel.repository.roomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class roomService {
    @Autowired
    roomRepository roomrepository;

    public Iterable<room> getAll() {
        return  roomrepository.findAll();
    }

    public room save(room r) {
        return roomrepository.save(r);
    }

    public room findById(int roomno) {
        return roomrepository.findById(roomno).orElse(null);
    }

    public void delete(room r) {
        roomrepository.delete(r);
    }

}
