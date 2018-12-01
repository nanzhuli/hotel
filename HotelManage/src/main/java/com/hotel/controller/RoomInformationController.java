package com.hotel.controller;

import com.hotel.model.result;
import com.hotel.model.room;
import com.hotel.model.standard;
import com.hotel.other.resultReturn;
import com.hotel.service.roomService;
import com.hotel.service.standardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomInformationController {
    @Autowired
    standardService standservice;

    @Autowired
    roomService roomservice;

    //查询房间标准列表
    @RequestMapping("/standard")
    public result<List<standard>> standardList() {
        return resultReturn.success(standservice.getAll());
    }

    //查询客房信息列表
    @RequestMapping("/room/roomlist")
    public result<List<room>> roomList() {
        return resultReturn.success(roomservice.getAll()) ;
    }

    //添加客房信息
    @PostMapping("/room/add")
    public result roomAdd(@RequestParam("roomno") int roomno, @RequestParam("type") int type,
                          @RequestParam("price") int price, @RequestParam("ifwindow") int ifwindow,
                          @RequestParam("comment") String comment) {
        System.out.println("qian"+roomno+"   "+roomservice.findById(roomno));
        //需要做检测
        if(roomservice.findById(roomno)==null) {
            System.out.println(roomno);
            room r = saveRoom(roomno, type, price, ifwindow, comment);
            return resultReturn.success(roomservice.save(r));
        }
        else
            return resultReturn.error(0,"roomno already exist!");
    }

    //更新已有信息
    @RequestMapping("/room/update/{roomno}")
    public result roomUpdate(@PathVariable("roomno") int roomno, @RequestParam("type") int type,
                             @RequestParam("price") int price, @RequestParam("ifwindow") int ifwindow,
                             @RequestParam("comment") String comment) {
        //需要先查询是否存在
        if(roomservice.findById(roomno)==null) {
            return resultReturn.error(0,"cant't find roomno!");
        }
        else {
            room r = saveRoom(roomno, type, price, ifwindow, comment);
            return resultReturn.success(roomservice.save(r));
        }
    }

    @RequestMapping("/room/searchOne/{roomno}")
    public result roomSerchOne(@PathVariable("roomno") int roomno) {
        room r = roomservice.findById(roomno);
        if(r == null) {
            return resultReturn.error(0,"it's not exist, you can't delete!");
        }
        else {
            return resultReturn.success(r);
        }
    }


    @RequestMapping("/room/delete/{roomno}")
    public result roomDelete(@PathVariable("roomno") int roomno) {
        //需要先查询是否存在
        room r = roomservice.findById(roomno);
        if(r == null) {
            return resultReturn.error(0,"it's not exist, you can't delete!");
        }
        else {
            roomservice.delete(r);
            return resultReturn.success(r);
        }
    }


    public room saveRoom(int roomno, int type, int price, int ifwindow, String comment) {
        room r = new room();
        r.setRoomno(roomno);
        r.setType(type);
        r.setPrice(price);
        r.setIfwindow(ifwindow);
        r.setComment(comment);
        return r;
    }


}
