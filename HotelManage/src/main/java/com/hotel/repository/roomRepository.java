package com.hotel.repository;

import com.hotel.model.room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface roomRepository extends JpaRepository<room, Integer> {

}
