package com.hotel.controller;

import com.hotel.model.room;
import com.hotel.model.standard;
import com.hotel.service.roomService;
import com.hotel.service.standardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class RoomInformationController {
    @Autowired
    standardService standservice;

    @Autowired
    roomService roomservice;

    @RequestMapping("/standard")
    public List<standard> standardList() {
        return standservice.getAll();
    }

    @RequestMapping("/room/roomlist")
    public List<room> roomList() {
        return roomservice.getAll();
    }

    @RequestMapping("/room/add")
    public String roomAdd(@RequestParam("roomno") int roomno, @RequestParam("type") int type,
                          @RequestParam("price") int price, @RequestParam("ifwindow") int ifwindow,
                          @RequestParam("comment") String comment) {

        room r = new room();
        r.setRoomno(roomno);
        r.setType(type);
        r.setPrice(price);
        r.setIfwindow(ifwindow);
        r.setComment(comment);
        roomservice.save(r);
        return "success";
    }

    @RequestMapping("/room/{roomno}")
    public String roomUpdate(@PathVariable("roomno") int roomno, @RequestParam("type") int type,
                             @RequestParam("price") int price, @RequestParam("ifwindow") int ifwindow,
                             @RequestParam("comment") String comment) {
        room r = new room();
        r.setRoomno(roomno);
        r.setType(type);
        r.setPrice(price);
        r.setIfwindow(ifwindow);
        r.setComment(comment);
        roomservice.save(r);
        return "update success";

    }


}
