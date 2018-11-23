package com.hotel.service;

import com.hotel.model.room;
import com.hotel.repository.roomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class roomService {
    @Autowired
    roomRepository roomrepository;

    public List<room> getAll() {
        return (List<room>) roomrepository.findAll();
    }

    public void save(room r) {
        roomrepository.save(r);
    }

}
