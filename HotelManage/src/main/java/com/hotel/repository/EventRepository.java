package com.hotel.repository;

import com.hotel.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
@org.springframework.stereotype.Repository
public interface EventRepository extends JpaRepository<Event, Integer>{

}
