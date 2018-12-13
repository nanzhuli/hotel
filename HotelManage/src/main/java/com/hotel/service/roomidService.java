package com.hotel.service;

import com.hotel.model.Roomid;
import com.hotel.repository.roomidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class roomidService {
    @Autowired
    roomidRepository roomidrepository;

    public List<Roomid> findAll(int roomno) {
        Roomid or = new Roomid();
        or.setRoomno(roomno);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("roomno",
                ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("rino","name",
                "id");
        Example<Roomid> ex = Example.of(or, exampleMatcher);
        List<Roomid> worker = roomidrepository.findAll(ex);
        return worker;
    }

    public Roomid findById(int r) {
        return roomidrepository.findById(r).orElse(null);
    }

    public List<Roomid> findAllWithoutparam() {
        return roomidrepository.findAll();
    }

    public Roomid save(Roomid e) {
        return roomidrepository.save(e);
    }

    public Roomid findByRino(int rino) {
        return roomidrepository.findById(rino).orElse(null);
    }

}
