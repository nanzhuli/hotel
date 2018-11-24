package com.hotel.repository;

import com.hotel.model.event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface eventRepository extends JpaRepository<event, Integer>{

}
