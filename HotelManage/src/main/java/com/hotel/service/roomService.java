package com.hotel.service;

import com.hotel.model.Room;
import com.hotel.repository.roomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class roomService {
    @Autowired
    roomRepository roomrepository;

    public Iterable<Room> getAll() {
        return  roomrepository.findAll();
    }

    public Room save(Room r) {
        return roomrepository.save(r);
    }

    public Room findById(int roomno) {
        return roomrepository.findById(roomno).orElse(null);
    }

    public void delete(Room r) {
        roomrepository.delete(r);
    }

    public List<Room> getEmpty(List<Integer> l) {
        //for(int i=0;i<l.size())
        List<Room> r =roomrepository.findByRoomnoNotIn(l);
        return r;
    }

}
