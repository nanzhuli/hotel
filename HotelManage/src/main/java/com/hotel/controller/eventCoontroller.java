package com.hotel.controller;

import com.hotel.model.event;
import com.hotel.model.result;
import com.hotel.other.resultReturn;
import com.hotel.repository.eventRepository;
import com.hotel.service.eventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

@RestController
public class eventCoontroller {
    @Autowired
    eventService eventservice;

    @RequestMapping("/event/allList")
    public result<event> eventList() {
        return resultReturn.success(eventservice.findAll());
    }

    @RequestMapping("/event/add")
    public result evenAdd(@RequestParam("type") int type, @RequestParam("roomno") int roomno,
                          @RequestParam("comment") String comment) {
        event e = new event();
        e.setType(type);
        e.setRoomno(roomno);
        e.setComment(comment);

        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        //System.out.println(timestamp);
        e.setStarttime(timestamp);

        //需要做
        return resultReturn.success();
    }

}
