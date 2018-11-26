package com.hotel.controller;

import com.hotel.model.employ;
import com.hotel.model.event;
import com.hotel.model.result;
import com.hotel.other.resultReturn;
import com.hotel.service.employService;
import com.hotel.service.eventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class EventController {
    @Autowired
    eventService eventservice;

    employService employservice;

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

        //需要做employno
        employ em = employservice.eventMatch(type, timestamp);
        if(em.getEmployno()==0){
            return resultReturn.error(0,"can't match worker in that time");
        }
        else{
            e.setEmployno(em.getEmployno());
            return resultReturn.success(eventservice.save(e));
        }
    }

    @RequestMapping("/event/update/{eventno}")
    public result eventUpdate(@PathVariable("eventno") int eventno, @RequestParam("type") int type,
                              @RequestParam("roomno") int roomno, @RequestParam("comment") String comment) {
        List<event> e = eventservice.findAllByEventno(eventno);
        if(e==null)
            return resultReturn.error(0,"can't find this eventno");
        event E = e.get(0);
        E.setType(type);
        E.setRoomno(roomno);
        E.setComment(comment);
        employ em = employservice.eventMatch(type, E.getStarttime());
        if(em.getEmployno()==0){
            return resultReturn.error(0,"can't match worker in that time");
        }
        else{
            return resultReturn.success(eventservice.save(E));
        }
    }

    @RequestMapping("/event/delete/{eventno}")
    public result eventDelete(@PathVariable("eventno") int eventno) {
        List<event> e = eventservice.findAllByEventno(eventno);
        if(e==null)
            return resultReturn.error(0,"can't find this eventno");
        event E = e.get(0);
        eventservice.delete(E);
        return resultReturn.success(E);
    }

}
