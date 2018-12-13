package com.hotel.service;

import com.hotel.model.Event;
import com.hotel.repository.eventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class eventService {
    @Autowired
    eventRepository eventrepository;

    public List<Event> findAll() {
        return eventrepository.findAll();
    }

    public Event save(Event e) {
        return eventrepository.save(e);
    }

    public Event findAllByEventno(int eventno) {
        return eventrepository.findById(eventno).orElse(null);
    }

    public void delete(Event e){
        eventrepository.delete(e);
    }

    public Event findById(int eventno) {
        return eventrepository.findById(eventno).orElse(null);
    }
}
