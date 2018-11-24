package com.hotel.controller;

import com.hotel.model.result;
import com.hotel.model.room;
import com.hotel.model.standard;
import com.hotel.other.resultReturn;
import com.hotel.repository.roomRepository;
import com.hotel.service.roomService;
import com.hotel.service.standardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/room/add")
    public result roomAdd(@RequestParam("roomno") int roomno, @RequestParam("type") int type,
                          @RequestParam("price") int price, @RequestParam("ifwindow") int ifwindow,
                          @RequestParam("comment") String comment) {
        //需要做检测
        if(roomservice.findById(roomno)==null) {
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
            /** 存疑 不知道这里可不可以使用 */
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
