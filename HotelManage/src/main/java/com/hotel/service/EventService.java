package com.hotel.service;

import com.hotel.model.Employ;
import com.hotel.model.Event;
import com.hotel.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService
{
    @Autowired
	EventRepository eventrepository;

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

    public List<Event> findAllByEmployno(int employno) {
        Event e = new Event();
        e.setEmployno(employno);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("employno",
                ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("eventno","type",
                "roomno","starttime","endtime","comment");
        Example<Event> ex = Example.of(e, exampleMatcher);
        List<Event> worker = eventrepository.findAll(ex);
        return worker;
    }
}
