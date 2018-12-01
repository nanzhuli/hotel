package com.hotel.service;

import com.hotel.model.roomid;
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

    public List<roomid> findAll(int roomno) {
        roomid or = new roomid();
        or.setRoomno(roomno);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("roomno",
                ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("rino","name",
                "id");
        Example<roomid> ex = Example.of(or, exampleMatcher);
        List<roomid> worker = roomidrepository.findAll(ex);
        return worker;
    }

    public List<roomid> findAllWithoutparam() {
        return roomidrepository.findAll();
    }

    public roomid save(roomid e) {
        return roomidrepository.save(e);
    }

    public roomid findByRino(int rino) {
        return roomidrepository.findById(rino).orElse(null);
    }

}
