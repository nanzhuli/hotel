package com.hotel.service;

import com.hotel.model.event;
import com.hotel.repository.eventRepository;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class eventService {
    @Autowired
    eventRepository eventrepository;

    public List<event> findAll() {
        return eventrepository.findAll();
    }

}
