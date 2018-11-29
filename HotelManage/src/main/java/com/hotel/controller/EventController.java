package com.hotel.controller;

import com.hotel.model.employ;
import com.hotel.model.event;
import com.hotel.model.result;
import com.hotel.other.resultReturn;
import com.hotel.service.employService;
import com.hotel.service.eventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
public class EventController {
    @Autowired
    employService employservice;

    @Autowired
    eventService eventservice;

    //查看事务列表
    @RequestMapping("/event/allList")
    public result<event> eventList() {
        return resultReturn.success(eventservice.findAll());
    }

    //添加新的事务
    @RequestMapping("/event/add")
    public result evenAdd(@RequestParam("type") int type, @RequestParam("roomno") int roomno,
                          @RequestParam("comment") String comment) {
        event e = new event();
        e.setType(type);
        e.setRoomno(roomno);
        e.setComment(comment);

        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
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

    //更新事务
    @RequestMapping("/event/update/{eventno}")
    public result eventUpdate(@PathVariable("eventno") int eventno, @RequestParam("type") int type,
                              @RequestParam("roomno") int roomno, @RequestParam("comment") String comment) {
        event E = eventservice.findAllByEventno(eventno);
        if(E==null)
            return resultReturn.error(0,"can't find this eventno");
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

    //根据eventno删除事务
    @RequestMapping("/event/delete/{eventno}")
    public result eventDelete(@PathVariable("eventno") int eventno) {
        event E = eventservice.findAllByEventno(eventno);
        if(E==null)
            return resultReturn.error(0,"can't find this eventno");
        eventservice.delete(E);
        return resultReturn.success(E);
    }

    @RequestMapping("/event/searchOne/{eventno}")
    public result evenSerchOne(@PathVariable("eventno") int eventno) {
        event e = eventservice.findById(eventno);
        if(e == null) {
            return resultReturn.error(0,"it's not exist, you can't delete!");
        }
        else {
            return resultReturn.success(e);
        }
    }

}
