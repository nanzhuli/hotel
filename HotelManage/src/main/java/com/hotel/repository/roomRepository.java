package com.hotel.repository;

import com.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface roomRepository extends JpaRepository<Room, Integer> {

    List<Room> findByRoomnoNotIn(List<Integer> age);
}
