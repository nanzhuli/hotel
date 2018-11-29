package com.hotel.service;

import com.hotel.model.event;
import com.hotel.repository.eventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class eventService {
    @Autowired
    eventRepository eventrepository;

    public List<event> findAll() {
        return eventrepository.findAll();
    }

    public event save(event e) {
        return eventrepository.save(e);
    }

    public event findAllByEventno(int eventno) {
        return eventrepository.findById(eventno).orElse(null);
    }

    public void delete(event e){
        eventrepository.delete(e);
    }

    public event findById(int eventno) {
        return eventrepository.findById(eventno).orElse(null);
    }
}
