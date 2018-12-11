package com.hotel.controller;

import com.hotel.model.Result;
import com.hotel.model.employ;
import com.hotel.model.event;
import com.hotel.model.room;
import com.hotel.other.resultReturn;
import com.hotel.service.employService;
import com.hotel.service.eventService;
import com.hotel.service.roomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EventController {
    @Autowired
    employService employservice;

    @Autowired
    eventService eventservice;

    @Autowired
    roomService roomservice;

    //查看事务列表
    @RequestMapping("/event/allList")
    public Result<event> eventList() {
        return resultReturn.success(eventservice.findAll());
    }

    //添加新的事务
    @RequestMapping("/event/add")
    public Result evenAdd(@RequestParam("type") int type,@RequestParam("roomno") int roomno,
						  @RequestParam("comment") String comment) {
        event e = new event();
        e.setType(type);
        e.setRoomno(roomno);
        e.setComment(comment);
        //检测房间号是否存在
        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        e.setStarttime(timestamp);

        room r = roomservice.findById(roomno);
        if(r==null)
            return resultReturn.error(1,"roomno is not exist");
        //需要做employno
        employ em = employservice.eventMatch(type, timestamp);
        if(em.getEmployno()==0){
            return resultReturn.error(1,"can't match worker in that time");
        }
        else{
            e.setEmployno(em.getEmployno());
            return resultReturn.success(eventservice.save(e));
        }
    }

    //更新事务
    @RequestMapping("/event/update/{eventno}")
    public Result eventUpdate(@PathVariable("eventno") int eventno,@RequestParam("type") int type,
							  @RequestParam("roomno") int roomno,@RequestParam("comment") String comment) {
        event E = eventservice.findAllByEventno(eventno);
        if(E==null)
            return resultReturn.error(1,"can't find this eventno");
        //更新时房间号确认
        E.setType(type);
        room r = roomservice.findById(roomno);
        if(r==null)
            return resultReturn.error(1,"roomno is not exist");
        E.setRoomno(roomno);
        E.setComment(comment);
        employ em = employservice.eventMatch(type, E.getStarttime());
        if(em.getEmployno()==0){
            return resultReturn.error(1,"can't match worker in that time");
        }
        else{
            E.setEmployno(em.getEmployno());
            return resultReturn.success(eventservice.save(E));
        }
    }

    //根据eventno删除事务
    @RequestMapping("/event/delete/{eventno}")
    public Result eventDelete(@PathVariable("eventno") int eventno) {
        event E = eventservice.findAllByEventno(eventno);
        if(E==null)
            return resultReturn.error(1,"can't find this eventno");
        eventservice.delete(E);
        return resultReturn.success(E);
    }

    @RequestMapping("/event/searchOne/{eventno}")
    public Result evenSerchOne(@PathVariable("eventno") int eventno) {
        event e = eventservice.findById(eventno);
        if(e == null) {
            return resultReturn.error(1,"it's not exist, you can't delete!");
        }
        else {
            return resultReturn.success(e);
        }
    }

}
